package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/*
Table:
id -> Long
patient_id -> Long
doctor_id -> Long
date -> Timestamp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "visit")
public class PatientVisit extends BaseEntity {

    @Column(name = "patient_details_id", updatable = false, nullable = false)
    private Long patientDetailsId;

    @Column(name = "doctor_details_id", updatable = false, nullable = false)
    private Long doctorDetailsId;

    @Column(name = "date_of_visit", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateOfVisit;
}
