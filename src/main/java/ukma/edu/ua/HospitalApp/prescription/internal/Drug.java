package ukma.edu.ua.HospitalApp.prescription.internal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ukma.edu.ua.HospitalApp.utility.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "drug")
public class Drug extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "producer", nullable = false)
  private String producer;

  @ManyToMany(mappedBy = "drugs")
  private List<Prescription> prescriptions;
}
