package ukma.edu.ua.HospitalApp.services;

import ukma.edu.ua.HospitalApp.models.Patient;

public interface PatientService {
    Patient createPatient(Patient patient);

    // Not full update
    Patient updatePatient(long id, Patient patient);

    void deletePatient(Patient patient);
}
