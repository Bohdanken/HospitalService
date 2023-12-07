package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "doctor")
public class Doctor extends User {
  public static User.Role ROLE = User.Role.DOCTOR;
  
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "doctor_type")
  @Enumerated(EnumType.STRING)
  private DoctorType doctorType;

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