package ukma.edu.ua.PrescriptionService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
  private Long patientId;

  @Lob
  @Column(name = "prescription_details", nullable = false, columnDefinition = "BLOB")
  private byte[] prescriptionDetails;
}
