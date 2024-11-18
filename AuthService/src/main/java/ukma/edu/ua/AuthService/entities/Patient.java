package ukma.edu.ua.AuthService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "patient")
public class Patient extends User {
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
}
