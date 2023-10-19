package ukma.edu.ua.HospitalApp.services;

import java.util.Objects;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.models.*;
import ukma.edu.ua.HospitalApp.repositories.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorService {
  private final DoctorRepository doctorRepository;

  public Doctor createDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
  }

  public Doctor updateDoctor(long id, Doctor doctor) {
    Doctor depDB = doctorRepository.findById(id)
        .get();

    if (Objects.nonNull(doctor.getFirstName())
        && !"".equalsIgnoreCase(
            doctor.getFirstName())) {
      depDB.setFirstName(
          doctor.getFirstName());
    }
    return doctorRepository.save(depDB);
  }

  public void deleteDoctor(Doctor doctor) {
    doctorRepository.delete(doctor);
  }

  public Prescription createPrescription(double patientId) {
    return new Prescription();
  }
}
