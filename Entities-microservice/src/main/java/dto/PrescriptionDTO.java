package ukma.edu.ua.HospitalApp.entities.src.main.java.dto;

import java.util.Date;
import lombok.Data;

@Data
public class PrescriptionDTO {
  private Long id;

  private Long patientId;

  private Date dateOfIssue;

  private DrugDTO[] drugs;
}