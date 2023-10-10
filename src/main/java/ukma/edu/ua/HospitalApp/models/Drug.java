package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Drug extends BaseEntity{
    String name;
    String producer;
}
