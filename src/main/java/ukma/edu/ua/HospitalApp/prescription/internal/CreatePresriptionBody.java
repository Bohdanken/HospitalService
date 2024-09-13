package ukma.edu.ua.HospitalApp.prescription.internal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class CreatePresriptionBody {
  @Min(1)
  private Long patientId;

  @Size(min = 1)
  private List<Long> drugs;
}
