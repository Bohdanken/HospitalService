package ukma.edu.ua.HospitalApp.dto;

import java.util.Date;
import lombok.Data;

@Data
public class PrescriptionDTO {
  private long id;

  private Date dateOfIssue;

  private DrugDTO[] drugs;
}