package ukma.edu.ua.HospitalApp.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Prescription extends BaseEntity{
    @OneToOne
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
