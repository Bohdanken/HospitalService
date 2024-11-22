package ukma.edu.ua.MedicineSourceService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.MedicineSourceService.entity.Drug;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByProducerContainingIgnoreCase(String producer);

    List<Drug> findByGenericNameContainingIgnoreCase(String genericName);

    Drug findByBrandName(String name);
}
