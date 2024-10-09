package ua.edu.ukma.medicinesourceservice.repository;

import ua.edu.ukma.medicinesourceservice.entity.Drug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByProducerContainingIgnoreCase(String producer);
    List<Drug> findByGenericNameContainingIgnoreCase(String genericName);
    Drug findByBrandName(String name);
}
