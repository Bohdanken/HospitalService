package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "app_user")
public class User extends BaseEntity {
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT,
  }

  public static class Roles {
    public static String ADMIN = String.valueOf(Role.ADMIN);

    public static String DOCTOR = String.valueOf(Role.DOCTOR);

    public static String PATIENT = String.valueOf(Role.PATIENT);
  }
}
