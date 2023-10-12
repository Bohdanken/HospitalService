package ukma.edu.ua.HospitalApp.dto;

import lombok.Data;

@Data
public class DoctorDTO {
  private long id;

  private String firstName;

  private String lastName;

  String email;

  int age;
}