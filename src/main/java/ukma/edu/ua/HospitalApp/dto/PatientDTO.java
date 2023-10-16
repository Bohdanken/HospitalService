package ukma.edu.ua.HospitalApp.dto;

import java.util.Date;
import lombok.Data;

@Data
public class PatientDTO {
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String address;

  private String passportNumber;

  private Date birthDate;
}