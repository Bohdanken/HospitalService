package ukma.edu.ua.HospitalApp.prescription;

import java.util.Date;
import lombok.Data;
import ukma.edu.ua.HospitalApp.prescription.dto.DrugDTO;

@Data
public class PrescriptionDTO {
  private Long id;

  private Long patientId;

  private Date dateOfIssue;

  private DrugDTO[] drugs;
}