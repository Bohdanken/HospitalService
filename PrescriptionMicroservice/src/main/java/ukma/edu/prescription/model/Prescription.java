package ukma.edu.prescription.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "prescription")
public class Prescription {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date_of_issue", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date dateOfIssue;

  @Column(name = "patient_id", nullable = false)
  private Long patientId;  // Storing the patient ID

  @Lob  // Large text field for storing the prescription details
  @Column(name = "prescription_details", nullable = false)
  private String prescriptionDetails;  // Storing the prescription content as a string
}
