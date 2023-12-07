package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "hospital")
public class Hospital extends BaseEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @OneToMany(targetEntity = Doctor.class)
  private List<Doctor> doctorList;
}
