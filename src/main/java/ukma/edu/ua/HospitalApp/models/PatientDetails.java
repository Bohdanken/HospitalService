package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "patient_details")
public class PatientDetails extends BaseEntity {
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "passport_number", nullable = false, unique = true)
  private String passportNumber;

  @Column(name = "birth_date", nullable = false)
  private Date birthDate;

  @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
  private Long userId;

  @OneToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "patientDetails")
  private List<Prescription> prescriptions;
}
