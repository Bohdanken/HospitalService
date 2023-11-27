package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "drug")
@Data
public class Drug extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "producer")
    private String producer;

    @ManyToMany(mappedBy = "drugs")
    private List<Prescription> prescriptions;
}
