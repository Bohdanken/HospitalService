package ukma.edu.ua.HospitalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.models.Doctor;
import ukma.edu.ua.HospitalApp.repositories.DoctorRepository;

@Service
public class DoctorService {
  private final DoctorRepository doctorRepository;

  @Autowired
  public DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }

  public Doctor createDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
  }

  public Doctor updateDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
  }

  public void deleteDoctor(Doctor doctor) {
    doctorRepository.delete(doctor);
  }
}
