package ukma.edu.ua.HospitalApp.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.Admin;
import ukma.edu.ua.HospitalApp.models.BaseEntity;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.repositories.AdminRepository;
import ukma.edu.ua.HospitalApp.repositories.DoctorRepository;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private DoctorRepository doctorRepository;
    @Autowired
	private PatientRepository patientRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails;
		Optional<Doctor> doctorOpt = doctorRepository.findByUsername(username);
		if (doctorOpt.isPresent()) {
			Doctor doctor = doctorOpt.get();
			userDetails = new org.springframework.security.core.userdetails.User(doctor.getFirstName()+doctor.getLastName(), doctor.password, Collections.singletonList(new SimpleGrantedAuthority("DOCTRO")));
		}
		else if (adminRepository.findByUsername(username).isPresent()) {
			Admin admin = adminRepository.findByUsername(username).get();
			userDetails = new org.springframework.security.core.userdetails.User(admin.name, admin.password, Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
		}
		else if (patientRepository.findByUsername(username).isPresent()) {
			Patient patient = patientRepository.findByUsername(username).get();
			userDetails = new org.springframework.security.core.userdetails.User(patient.firstName+patient.lastName, patient.password, Collections.singletonList(new SimpleGrantedAuthority("PATIENT")));
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return userDetails;
	}

}

