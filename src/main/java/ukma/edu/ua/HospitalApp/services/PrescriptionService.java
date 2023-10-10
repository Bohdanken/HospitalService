package ukma.edu.ua.HospitalApp.services;

import ukma.edu.ua.HospitalApp.models.Prescription;

public interface PrescriptionService {
    Prescription createPrescription(Prescription prescription);

    // Not full update
    Prescription updatePrescription(long id, Prescription prescription);

    void deletePrescription(Prescription prescription);
}
