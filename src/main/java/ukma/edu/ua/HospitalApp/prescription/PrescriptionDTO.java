package ukma.edu.ua.HospitalApp.prescription;

import java.util.Date;
import lombok.Data;

@Data
public class PrescriptionDTO {
  private Long id;

  private Long patientId;

  private Date dateOfIssue;

  private DrugDTO[] drugs;
}