package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Hospital extends BaseEntity{
    String name;
    String address;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "doctor_id")
    List<Doctor> doctorList;
}
