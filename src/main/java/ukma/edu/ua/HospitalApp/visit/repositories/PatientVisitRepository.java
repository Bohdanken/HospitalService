package ukma.edu.ua.HospitalApp.visit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.entities.internal.PatientVisit;


import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit, Long> {
    List<PatientVisit> findByPatientDetailsId(Long patientDetailsId);
    List<PatientVisit> findByDoctorDetailsId(Long doctorDetailsId);

    @Query("SELECT v FROM PatientVisit v WHERE v.doctorDetails.id = :doctorId AND v.dateOfVisit BETWEEN :startTime AND :endTime")
    List<PatientVisit> findAppointmentsForDoctorInRange(Long doctorId, Timestamp startTime, Timestamp endTime);
}
