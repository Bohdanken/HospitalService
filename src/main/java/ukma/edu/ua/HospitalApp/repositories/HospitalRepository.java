package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD:src/main/java/ukma/edu/ua/HospitalApp/Database/HospitalRepository.java
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.models.BaseEntity;
=======
>>>>>>> main:src/main/java/ukma/edu/ua/HospitalApp/repositories/HospitalRepository.java
import ukma.edu.ua.HospitalApp.models.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}