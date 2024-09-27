package ukma.edu.ua.HospitalApp.prescription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.entities.internal.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}