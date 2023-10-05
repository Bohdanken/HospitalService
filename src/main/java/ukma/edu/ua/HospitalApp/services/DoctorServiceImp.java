package ukma.edu.ua.HospitalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.Database.DoctorRepository;
import ukma.edu.ua.HospitalApp.models.*;

import java.util.*;

@Service
public class DoctorServiceImp implements DoctorService {


    private DoctorRepository doctorRepository;
/*
    @Autowired
    public DoctorServiceImp(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

 */

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(long id, Doctor doctor) {
        Doctor depDB
                = doctorRepository.findById(id)
                .get();

        if (Objects.nonNull(doctor.getFirstName())
                && !"".equalsIgnoreCase(
                doctor.getFirstName())) {
            depDB.setFirstName(
                    doctor.getFirstName());
        }

        return doctorRepository.save(depDB);
    }

    @Override
    public void deleteDoctor(Doctor doctor)
    {
        doctorRepository.delete(doctor);
    }

    @Override
    public Prescription createPrescription(double patientId) {
        Prescription prescription= new Prescription();
        return prescription;
    }
}
