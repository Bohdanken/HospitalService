package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "prescription")
public class Prescription extends BaseEntity {
  @Column(name = "date_of_issue")
  @Temporal(TemporalType.DATE)
  private Date dateOfIssue;

  @ManyToOne(targetEntity = Patient.class)
  @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;

  @ManyToMany(targetEntity = Drug.class, fetch = FetchType.LAZY)
  @JoinTable(
      name = "prescription_drug_map",
      joinColumns = @JoinColumn(name = "prescription_id"),
      inverseJoinColumns = @JoinColumn(name = "drug_id")
  )
  private List<Drug> drugs;
}
