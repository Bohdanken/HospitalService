package ukma.edu.ua.HospitalApp.entities;

import java.util.Date;
import lombok.Data;
import ukma.edu.ua.HospitalApp.entities.internal.DoctorDetails;

@Data
public class DoctorDTO {
  private Long id;

  private String email;

  private String firstName;

  private String lastName;

  private Date birthDate;

  private DoctorDetails.DoctorType doctorType;

  private HospitalDTO hospital;
}