package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "drug")
public class Drug extends BaseEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "producer")
  private String producer;

  @ManyToMany(mappedBy = "drugs")
  private List<Prescription> prescriptions;
}
