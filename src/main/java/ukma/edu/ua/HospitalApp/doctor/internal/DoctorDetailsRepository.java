package ukma.edu.ua.HospitalApp.doctor.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {
}