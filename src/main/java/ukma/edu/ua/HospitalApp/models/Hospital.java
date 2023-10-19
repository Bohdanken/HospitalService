package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "doctor_id")
  public List<Doctor> doctorList;
}
