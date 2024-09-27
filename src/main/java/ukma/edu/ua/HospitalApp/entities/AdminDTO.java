package ukma.edu.ua.HospitalApp.entities;

import lombok.Data;

@Data
public class AdminDTO {
  private Long id;

  private String email;

  private String firstName;

  private String lastName;
}