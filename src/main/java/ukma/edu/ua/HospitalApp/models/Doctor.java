package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
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

  private final Role role = Role.DOCTOR;

  public Doctor(String firstName, String lastName, String email, int age, DoctorType doctorType, Hospital hospital) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.doctorType = doctorType;
    this.hospital = hospital;
    //ThreadContext.put(String.valueOf( this.getId()),"ID");
  }

  @ManyToOne(targetEntity = Hospital.class)
  @JoinColumn(name = "hospital_id", nullable = false)
  public Hospital hospital;

  public enum DoctorType {
    CARDIOLOGIST,
    THERAPIST,
    ORTHOPEDIST,
    DENTIST,
    COSMETOLOGIST
  }
}
