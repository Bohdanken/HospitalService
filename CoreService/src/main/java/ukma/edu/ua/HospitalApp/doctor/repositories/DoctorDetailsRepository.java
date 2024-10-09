package ukma.edu.ua.HospitalApp.doctor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.common.entities.Doctor;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<Doctor, Long> {
}