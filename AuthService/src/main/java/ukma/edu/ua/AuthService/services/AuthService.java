package ukma.edu.ua.AuthService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ukma.edu.ua.AuthService.dto.LoginBody;
import ukma.edu.ua.AuthService.dto.RegisterDoctorBody;
import ukma.edu.ua.AuthService.dto.RegisterPatientBody;
import ukma.edu.ua.AuthService.entities.Doctor;
import ukma.edu.ua.AuthService.entities.Patient;
import ukma.edu.ua.AuthService.entities.User.Role;
import ukma.edu.ua.AuthService.repositories.DoctorRepository;
import ukma.edu.ua.AuthService.repositories.PatientRepository;
import ukma.edu.ua.AuthService.security.AppUser;
import ukma.edu.ua.AuthService.services.JWTService.TokenResponse;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final AuthenticationConfiguration auth;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public TokenResponse login(LoginBody data) {
        var user = authenticate(data.getEmail(), data.getPassword());
        return jwtService.generateToken(user);
    }

    public TokenResponse registerPatient(RegisterPatientBody data) {
        var patient = Patient.builder()
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.PATIENT)
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .address(data.getAddress())
                .birthDate(data.getBirthDate())
                .passportNumber(data.getPassportNumber())
                .build();

        try {
            patientRepository.saveAndFlush(patient);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified credentials already exists");
        }

        try {
            jmsTemplate.convertAndSend("user_created", objectMapper.writeValueAsString(patient));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        return jwtService.generateToken(patient);
    }

    public TokenResponse registerDoctor(RegisterDoctorBody data) {
        var doctor = Doctor.builder()
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.DOCTOR)
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .doctorType(data.getDoctorType())
                .build();

        try {
            doctorRepository.saveAndFlush(doctor);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified credentials already exists");
        }

        try {
            jmsTemplate.convertAndSend("user_created", objectMapper.writeValueAsString(doctor));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        return jwtService.generateToken(doctor);
    }

    public AppUser authenticate(String email, String password) {
        try {
            var user = userDetailsService.loadUserByUsername(email);
            auth.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return (AppUser) user;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
    }
}