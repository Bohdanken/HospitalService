package ukma.edu.ua.HospitalApp.doctor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.doctor.dto.UpdateDoctorBody;
import ukma.edu.ua.HospitalApp.doctor.mappers.DoctorMapper;
import ukma.edu.ua.HospitalApp.doctor.repositories.DoctorDetailsRepository;
import ukma.edu.ua.HospitalApp.common.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.common.entities.Doctor;

@Service
@RequiredArgsConstructor
public class DoctorServiceInternal {
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final DoctorMapper doctorMapper;

    public DoctorDTO updateDoctor(Long doctorId, UpdateDoctorBody data) {
        Doctor existingDoctor = doctorDetailsRepository
                .findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        existingDoctor.setEmail(data.getEmail());
        existingDoctor.setDoctorType(data.getDoctorType());
        existingDoctor.setBirthDate(data.getBirthDate());
        existingDoctor.setFirstName(data.getFirstName());
        existingDoctor.setLastName(data.getLastName());

        Doctor savedDoctor = doctorDetailsRepository.save(existingDoctor);
        return doctorMapper.doctorToDoctorDTO(savedDoctor);
    }

    public Doctor getDoctorById(long id) {
        return doctorDetailsRepository.findById(id).orElse(null);
    }

    public Doctor saveDoctorData(Doctor data) {
        return doctorDetailsRepository.save(data);
    }
}
