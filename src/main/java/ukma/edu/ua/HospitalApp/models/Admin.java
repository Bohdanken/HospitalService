package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "admin")
public class Admin extends User {
  public static User.Role ROLE = User.Role.ADMIN;
}
