package ukma.edu.ua.HospitalApp.services;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.models.Hospital;

@Service
public class HospitalService {
    public void addDoctor(Hospital hospital, Doctor doctor){
        hospital.doctorList.add(doctor);
    }
}
