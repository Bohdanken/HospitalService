package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {
  @Column(name = "first_name")
  public String firstName;

  @Column(name = "last_name")
  public String lastName;

  @Column(name = "email")
  public String email;

  @Column(name = "address")
  public String address;

  @Column(name = "passport_number")
  public String passportNumber;

  @Column(name = "birth_date")
  public Date birthDate;

  @OneToMany(mappedBy = "patient")
  public List<Prescription> prescriptions;
}
