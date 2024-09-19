package ukma.edu.ua.HospitalApp.doctor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.entities.internal.DoctorDetails;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {
}