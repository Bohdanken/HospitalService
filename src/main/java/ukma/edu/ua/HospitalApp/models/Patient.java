package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
public class Patient extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    private final Role role = Role.PATIENT;

    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;
}
