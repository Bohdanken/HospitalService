package ukma.edu.ua.HospitalApp.prescription.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class CreatePresriptionBody {
  @Min(1)
  @NotNull
  private Long patientId;

  @Size(min = 1)
  @NotNull
  private List<CreatePresriptionBodyDrugInfo> drugs;

  @Data
  public static class CreatePresriptionBodyDrugInfo {
    @NotNull
    private Long drugId;

    @NotNull
    @Min(1)
    private Integer timesPerDay;
  }
}