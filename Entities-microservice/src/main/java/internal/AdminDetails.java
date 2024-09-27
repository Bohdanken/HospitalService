package ukma.edu.ua.HospitalApp.entities.src.main.java.internal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "admin_details")
public class AdminDetails extends BaseEntity {
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
  private Long userId;

  @OneToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
