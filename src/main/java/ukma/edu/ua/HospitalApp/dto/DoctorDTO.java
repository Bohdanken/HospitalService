package ukma.edu.ua.HospitalApp.dto;

import lombok.Data;
import ukma.edu.ua.HospitalApp.models.Doctor;

@Data
public class DoctorDTO {
  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private Doctor.DoctorType doctorType;
}