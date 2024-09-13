package ukma.edu.ua.HospitalApp.prescription.internal;

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
import ukma.edu.ua.HospitalApp.utility.BaseEntity;
import ukma.edu.ua.HospitalApp.patient.internal.PatientDetails;

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
