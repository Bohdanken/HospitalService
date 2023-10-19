package ukma.edu.ua.HospitalApp.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.models.*;
import ukma.edu.ua.HospitalApp.repositories.DoctorRepository;

import java.util.*;

@Service
public class DoctorService {
    ModelMapper modelMapper;
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper=modelMapper;
    }


    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    //Not full update
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

    public void deleteDoctor(Doctor doctor)
    {
        doctorRepository.delete(doctor);
    }

    public Prescription createPrescription(double patientId) {
        Prescription prescription= new Prescription();
        return prescription;
    }
    private Doctor convertDtoToEntity(DoctorDTO DoctorDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Doctor doctor = modelMapper.map(DoctorDTO,Doctor.class);
        return doctor;
    }
}
