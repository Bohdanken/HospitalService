package ukma.edu.ua.PrescriptionService.security;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
	private Long id;
	private String email;
	private UserRole role;

	public enum UserRole {
		ADMIN,
		DOCTOR,
		PATIENT,
	}

	public static interface Roles {
		public static final String ADMIN = "ADMIN";
		public static final String DOCTOR = "DOCTOR";
		public static final String PATIENT = "PATIENT";
	}
}
