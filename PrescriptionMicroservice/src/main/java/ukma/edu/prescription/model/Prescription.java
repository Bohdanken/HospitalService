package ukma.edu.prescription.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

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
  private Long patientId;  // Storing the patient ID instead of the entity reference

  @ElementCollection
  @CollectionTable(name = "prescription_drugs", joinColumns = @JoinColumn(name = "prescription_id"))
  @Column(name = "drug_id")
  private List<Long> drugIds;  // Store the IDs of the drugs instead of the Drug entity
}
