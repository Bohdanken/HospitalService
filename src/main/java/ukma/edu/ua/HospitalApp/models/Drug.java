package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;
@Entity
@Data
@Table(name = "drug")
public class Drug extends BaseEntity {
  @Column(name = "name")
  public String name;

  @Column(name = "producer")
  public String producer;

  @ManyToMany(mappedBy = "drugs")
  public List<Prescription> prescriptions;
}
