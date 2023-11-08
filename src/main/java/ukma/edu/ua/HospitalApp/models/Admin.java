package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends BaseEntity {
    private final Role role = Role.ADMIN;
    public String name;
}
