package ukma.edu.ua.HospitalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukma.edu.ua.HospitalApp.models.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}