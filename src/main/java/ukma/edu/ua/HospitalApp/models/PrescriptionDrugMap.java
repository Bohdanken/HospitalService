package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@SuppressWarnings("checkstyle:OneTopLevelClass")
@AllArgsConstructor
@Data
class PrescriptionDrugMapId implements Serializable {
  private Long prescriptionId;

  private Long drugId;
}

@Data
@Entity
@Table(name = "prescription_drug_map")
@IdClass(PrescriptionDrugMapId.class)
public class PrescriptionDrugMap {
  @Id
  @Column(name = "prescription_id", insertable = false, updatable = false, nullable = false)
  private Long prescriptionId;

  @Id
  @Column(name = "drug_id", insertable = false, updatable = false, nullable = false)
  private Long drugId;

  @ManyToOne(targetEntity = Prescription.class)
  @JoinColumn(name = "prescription_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Prescription prescription;

  @ManyToOne(targetEntity = Drug.class)
  @JoinColumn(name = "drug_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Drug drug;
}
