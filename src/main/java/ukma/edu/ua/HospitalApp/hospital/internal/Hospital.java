package ukma.edu.ua.HospitalApp.hospital.internal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ukma.edu.ua.HospitalApp.doctor.internal.DoctorDetails;
import ukma.edu.ua.HospitalApp.utility.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hospital")
public class Hospital extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "address", nullable = false)
  private String address;

  @OneToMany(targetEntity = DoctorDetails.class)
  private List<DoctorDetails> doctorList;
}
