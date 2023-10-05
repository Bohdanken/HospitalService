package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Drug extends BaseEntity{
    String name;
    String producer;
}
