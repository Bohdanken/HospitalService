package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
import java.util.List;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "passport_number")
  private String passportNumber;

  @Column(name = "birth_date")
  @Temporal(TemporalType.DATE)
  private Date birthDate;

  @OneToMany(mappedBy = "patient")
  private List<Prescription> prescriptions;
}
