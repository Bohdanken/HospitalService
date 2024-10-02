package ukma.edu.ua.HospitalApp.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.doctor.services.DoctorServiceInternal;
import ukma.edu.ua.HospitalApp.common.entities.Doctor;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorServiceInternal doctorServiceInternal;

    public Doctor getDoctorById(long id) {
        return doctorServiceInternal.getDoctorById(id);
    }

    public Doctor saveDoctorData(Doctor data) {
        return doctorServiceInternal.saveDoctorData(data);
    }
}
