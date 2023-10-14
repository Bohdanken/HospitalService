package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Prescription extends BaseEntity{
    String name;
    @ManyToOne
    Patient patient;
    @Temporal(TemporalType.DATE)
    Date dateOfIssue;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Prescripted",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id"))
    private List<Drug> drugList;
}
