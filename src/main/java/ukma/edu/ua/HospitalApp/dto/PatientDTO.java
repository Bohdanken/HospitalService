package ukma.edu.ua.HospitalApp.dto;

import lombok.Data;

@Data
public class PatientDTO {
  String firstName;

  String lastName;

  String email;

  String address;

  String passportNumber;

  int yearOfBirth;

  private long id;

}