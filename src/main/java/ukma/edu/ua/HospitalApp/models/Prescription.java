package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "prescription")
public class Prescription extends BaseEntity {
  @Column(name = "date_of_issue", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date dateOfIssue;

  @Column(name = "patient_details_id", updatable = false, insertable = false, nullable = false)
  private Long patientDetailsId;

  @ManyToOne(targetEntity = PatientDetails.class)
  @JoinColumn(name = "patient_details_id", nullable = false)
  private PatientDetails patientDetails;

  @ManyToMany(targetEntity = Drug.class)
  @JoinTable(
      name = "prescription_drug_map",
      joinColumns = @JoinColumn(name = "prescription_id"),
      inverseJoinColumns = @JoinColumn(name = "drug_id"))
  private List<Drug> drugs;
}
