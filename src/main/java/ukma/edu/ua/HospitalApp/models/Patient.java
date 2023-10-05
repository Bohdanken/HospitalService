package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Patient extends Person{
    String email;
    String address;
    String passportNumber;
    int yearOfBirth;
}
