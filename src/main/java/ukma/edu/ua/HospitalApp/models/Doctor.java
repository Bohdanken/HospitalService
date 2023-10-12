package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("checkstyle:OneTopLevelClass")
enum DoctorType {
  CARDIOLOGIST,
  THERAPIST,
  ORTHOPEDIST,
  DENTIST,
  COSMETOLOGIST
}

@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {
  @Column(name = "first_name")
  public String firstName;

  @Column(name = "last_name")
  public String lastName;

  @Column(name = "email")
  public String email;

  @Column(name = "age")
  public int age;

  @Column(name = "doctor_type")
  @Enumerated(EnumType.STRING)
  public DoctorType doctorType;

  @ManyToOne(targetEntity = Hospital.class)
  @JoinColumn(name = "hospital_id", nullable = false)
  public Hospital hospital;
}
