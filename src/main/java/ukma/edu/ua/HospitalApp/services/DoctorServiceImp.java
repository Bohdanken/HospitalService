package ukma.edu.ua.HospitalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.*;

import java.util.*;

@Service
public class DoctorServiceImp implements DoctorService {
    private final Set<Doctor> doctors=new HashSet<Doctor>();

    @Override
    public void createDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    @Override
    public Prescription createPrescription(double patientId) {
        Prescription prescription= new Prescription();
        return prescription;
    }
}
