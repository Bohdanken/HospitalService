package ukma.edu.ua.HospitalApp.patient.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.common.dto.PatientDTO;
import ukma.edu.ua.HospitalApp.common.entities.Patient;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
  PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

  PatientDTO patientToPatientDTO(Patient patient);
}
