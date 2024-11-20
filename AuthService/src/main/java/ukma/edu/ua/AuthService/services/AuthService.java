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
import ukma.edu.ua.AuthService.dto.LoginBody;
import ukma.edu.ua.AuthService.dto.RegisterDoctorBody;
import ukma.edu.ua.AuthService.dto.RegisterPatientBody;
import ukma.edu.ua.AuthService.entities.Doctor;
import ukma.edu.ua.AuthService.entities.Patient;
import ukma.edu.ua.AuthService.entities.User;
import ukma.edu.ua.AuthService.entities.User.Role;
import ukma.edu.ua.AuthService.repositories.DoctorRepository;
import ukma.edu.ua.AuthService.repositories.PatientRepository;
import ukma.edu.ua.AuthService.repositories.UserRepository;
import ukma.edu.ua.AuthService.security.AppUser;
import ukma.edu.ua.AuthService.services.JWTService.TokenResponse;

import java.sql.SQLIntegrityConstraintViolationException;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final AuthenticationConfiguration auth;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public TokenResponse login(LoginBody data) {
        var user = authenticate(data.getEmail(), data.getPassword());
        return jwtService.generateToken(user);
    }

    public TokenResponse registerPatient(RegisterPatientBody data) {
        var user = User
                .builder()
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.PATIENT)
                .build();

        try {
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified credentials already exists");
        }

        try {
            jmsTemplate.convertAndSend("user_created", objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failure to convert user object to JSON");
        }

        var patient = Patient.builder()
                .id(user.getId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .address(data.getAddress())
                .birthDate(data.getBirthDate())
                .passportNumber(data.getPassportNumber())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .role(user.getRole())
                .build();
        patientRepository.save(patient);

        return jwtService.generateToken(user);
    }

    public TokenResponse registerDoctor(RegisterDoctorBody data) {
        var user = User
                .builder()
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.DOCTOR)
                .build();

        try {
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified credentials already exists");
        }

        var doctorDetails = Doctor.builder()
                .id(user.getId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .doctorType(data.getDoctorType())
                .build();
        doctorRepository.save(doctorDetails);

        return jwtService.generateToken(user);
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