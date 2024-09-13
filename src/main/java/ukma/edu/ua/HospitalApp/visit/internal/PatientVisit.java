package ukma.edu.ua.HospitalApp.visit.internal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetails;
import ukma.edu.ua.HospitalApp.utility.BaseEntity;
import ukma.edu.ua.HospitalApp.patient.internal.PatientDetails;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "visit")
public class PatientVisit extends BaseEntity {

//    @Column(name = "patient_details_id", updatable = false, nullable = false)
//    private Long patientDetailsId;
//
//    @Column(name = "doctor_details_id", updatable = false, nullable = false)
//    private Long doctorDetailsId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_details_id", nullable = false)
    private PatientDetails patientDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_details_id", nullable = false)
    private DoctorDetails doctorDetails;

    @Column(name = "date_of_visit", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateOfVisit;

}
