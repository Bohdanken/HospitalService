package ukma.edu.ua.VisitService.security;

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
		public static String ADMIN = String.valueOf(UserRole.ADMIN);
		public static String DOCTOR = String.valueOf(UserRole.DOCTOR);
		public static String PATIENT = String.valueOf(UserRole.PATIENT);
	}
}
