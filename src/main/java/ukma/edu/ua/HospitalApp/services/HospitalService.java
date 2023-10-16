package ukma.edu.ua.HospitalApp.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.models.Hospital;

@Service
public class HospitalService {
  public void addDoctor(Hospital hospital, Doctor doctor) {
    ArrayList<Doctor> doctors = new ArrayList<>();
    doctors.add(doctor);
    hospital.setDoctorList(doctors);
  }
}
