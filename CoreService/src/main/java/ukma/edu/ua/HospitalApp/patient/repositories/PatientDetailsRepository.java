package ukma.edu.ua.HospitalApp.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ukma.edu.ua.HospitalApp.common.entities.Patient;

@Repository
public interface PatientDetailsRepository extends JpaRepository<Patient, Long> {
}