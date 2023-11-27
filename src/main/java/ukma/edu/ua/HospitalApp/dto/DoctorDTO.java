package ukma.edu.ua.HospitalApp.dto;

import lombok.Data;
import ukma.edu.ua.HospitalApp.models.*;


@Data
public class DoctorDTO {
  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private int age;

  private Doctor.DoctorType doctorType;
}