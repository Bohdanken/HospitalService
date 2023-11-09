package ukma.edu.ua.HospitalApp.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MLoginBody {
    @NotNull()
    @Email()
    String email;

    @NotEmpty()
    String password;

    @NotEmpty()
    String type_staff;
}
