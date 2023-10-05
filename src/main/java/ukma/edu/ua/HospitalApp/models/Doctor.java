package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;
import lombok.Data;

enum DoctorType {
    CARDIOLOGIST,
    THERAPIST,
    ORTHOPEDIST,
    DENTIST,
    COSMETOLOGIST
}

@Entity
@Data
@Table(name = "doctors")
public class Doctor extends Person{
    @Column(name = "email")
    String email;
    @Column(name = "age")
    int age;
    @Column(name = "doctorType")
   DoctorType doctorType;
}
