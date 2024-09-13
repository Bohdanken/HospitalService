package ukma.edu.ua.HospitalApp.doctor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ukma.edu.ua.HospitalApp.auth.User;
import ukma.edu.ua.HospitalApp.utility.BaseEntity;
import ukma.edu.ua.HospitalApp.hospital.Hospital;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "doctor_details")
public class DoctorDetails extends BaseEntity {
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "birth_date", nullable = false)
  private Date birthDate;

  @Column(name = "doctor_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private DoctorType doctorType;

  @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
  private Long userId;

  @OneToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(targetEntity = Hospital.class)
  @JoinColumn(name = "hospital_id", nullable = false)
  private Hospital hospital;

  public enum DoctorType {
    CARDIOLOGIST,
    THERAPIST,
    ORTHOPEDIST,
    DENTIST,
    COSMETOLOGIST
  }
}