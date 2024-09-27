package ukma.edu.ua.HospitalApp.entities;

import lombok.Data;
import ukma.edu.ua.HospitalApp.entities.internal.User;

@Data
public class UserDTO {
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(userDTO.getFirstName());
        userDTO.setLastName(userDTO.getLastName());
        return userDTO;
    }
}
