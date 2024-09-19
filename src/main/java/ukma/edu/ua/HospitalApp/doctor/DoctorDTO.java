package ukma.edu.ua.HospitalApp.doctor;

import java.util.Date;
import lombok.Data;
import ukma.edu.ua.HospitalApp.entities.DoctorDetails;
import ukma.edu.ua.HospitalApp.hospital.HospitalDTO;

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