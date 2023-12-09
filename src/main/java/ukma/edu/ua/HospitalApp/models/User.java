package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public abstract class User extends BaseEntity {
  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT
  }

  public static class Roles {
    public static String ADMIN = String.valueOf(Role.ADMIN);

    public static String DOCTOR = String.valueOf(Role.DOCTOR);

    public static String PATIENT = String.valueOf(Role.PATIENT);
  }
}
