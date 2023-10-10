package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Patient extends Person{
    String email;
    String address;
    String passportNumber;
    int yearOfBirth;
}
