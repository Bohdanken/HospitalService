package ukma.edu.ua.HospitalApp.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ukma.edu.ua.HospitalApp.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.models.Patient;
import ukma.edu.ua.HospitalApp.repositories.PatientRepository;

import java.util.Objects;

    @Service
    public class PatientService{
        ModelMapper modelMapper;
        private PatientRepository patientRepository;

        @Autowired
        public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
            this.patientRepository = patientRepository;
            this.modelMapper = modelMapper;
        }


        public Patient createPatient(Patient patient) {
            return patientRepository.save(patient);
        }

        // Not full update
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


        public void deletePatient(Patient patient) {
            patientRepository.delete(patient);
        }
    }
