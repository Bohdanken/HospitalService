package ukma.edu.ua.HospitalApp.models;

import java.util.Date;

public class Prescription extends BaseEntity{
    Patient patient;
    Date dateOfIssue;
    Drug[] drugs;
}
