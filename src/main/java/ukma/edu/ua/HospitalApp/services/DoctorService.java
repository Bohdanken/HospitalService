package ukma.edu.ua.HospitalApp.services;

import ukma.edu.ua.HospitalApp.models.*;

public interface DoctorService {
    void createDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Doctor doctor);

    Prescription createPrescription(double patientId);
}
