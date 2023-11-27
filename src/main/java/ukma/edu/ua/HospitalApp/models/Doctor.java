package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "doctor_type")
    @Enumerated(EnumType.STRING)
    private DoctorType doctorType;

    @ManyToOne(targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    public enum DoctorType {
        CARDIOLOGIST,
        THERAPIST,
        ORTHOPEDIST,
        DENTIST,
        COSMETOLOGIST
    }

    // Constructor, getters, and setters (if necessary)
}

        THERAPIST,
        ORTHOPEDIST,
        DENTIST,
        COSMETOLOGIST
    }

    // Constructor, getters, and setters (if necessary)
}
