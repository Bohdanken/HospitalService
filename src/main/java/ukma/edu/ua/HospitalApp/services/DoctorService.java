package ukma.edu.ua.HospitalApp.services;

import ukma.edu.ua.HospitalApp.models.*;

import javax.print.Doc;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(long id, Doctor doctor);
    void deleteDoctor(Doctor doctor);
    Prescription createPrescription(double patientId);
}
