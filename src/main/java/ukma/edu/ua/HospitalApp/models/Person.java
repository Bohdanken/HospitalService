
package ukma.edu.ua.HospitalApp.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object representing an person.
 */
@MappedSuperclass
@Getter
@Setter
public class Person extends BaseEntity {

	@Column(name = "first_name")
	private String firstName="None";

	@Column(name = "last_name")
	private String lastName="None";
}
