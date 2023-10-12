package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital extends BaseEntity {
  @Column(name = "name")
  public String name;

  @Column(name = "address")
  public String address;

  @OneToMany(targetEntity = Doctor.class)
  public List<Doctor> doctorList;
}
