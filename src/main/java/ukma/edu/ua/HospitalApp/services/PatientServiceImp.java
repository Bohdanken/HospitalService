package ukma.edu.ua.HospitalApp.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.edu.ua.HospitalApp.Database.PatientDTO;
import ukma.edu.ua.HospitalApp.Database.PatientRepository;
import ukma.edu.ua.HospitalApp.models.Patient;

import java.util.Objects;

    @Service
    public class PatientServiceImp implements PatientService {
        ModelMapper modelMapper;
        private PatientRepository patientRepository;

        @Autowired
        public PatientServiceImp(PatientRepository patientRepository, ModelMapper modelMapper) {
            this.patientRepository = patientRepository;
            this.modelMapper = modelMapper;
        }

        @Override
        public Patient createPatient(Patient patient) {
            return patientRepository.save(patient);
        }

        // Not full update
        @Override
        public Patient updatePatient(long id, Patient patient) {
            Patient depDB = patientRepository.findById(id).get();

            if (Objects.nonNull(patient.getFirstName()) && !"".equalsIgnoreCase(patient.getFirstName())) {
                depDB.setFirstName(patient.getFirstName());
            }
            return patientRepository.save(depDB);
        }

        Patient convertDtoToEntity(PatientDTO patientDTO) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            Patient patient = modelMapper.map(patientDTO, Patient.class);
            return patient;
        }

        @Override
        public void deletePatient(Patient patient) {
            patientRepository.delete(patient);
        }
    }
