package ukma.edu.ua.VisitService.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "visit")
public class PatientVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "patient_full_name", nullable = false)
    private String patientFullName;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "doctor_full_name", nullable = false)
    private String doctorFullName;

    @Column(name = "date_of_visit", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateOfVisit;
}
