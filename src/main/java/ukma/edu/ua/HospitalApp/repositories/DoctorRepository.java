package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  public Doctor findByEmail(String email);
}

