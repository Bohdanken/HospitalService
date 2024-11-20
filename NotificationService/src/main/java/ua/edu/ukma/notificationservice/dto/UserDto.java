package ua.edu.ukma.notificationservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private Role role;

    public enum Role {
        ADMIN,
        DOCTOR,
        PATIENT,
    }

    public static class Roles {
        public static String ADMIN = String.valueOf(Role.ADMIN);

        public static String DOCTOR = String.valueOf(Role.DOCTOR);

        public static String PATIENT = String.valueOf(Role.PATIENT);
    }
}
