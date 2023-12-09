package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "patient")
public class Patient extends User {
  public static User.Role ROLE = Role.PATIENT;
  
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "address")
  private String address;

  @Column(name = "passport_number")
  private String passportNumber;

  @Column(name = "birth_date")
  private Date birthDate;

  @OneToMany(mappedBy = "patient")
  private List<Prescription> prescriptions;
}
