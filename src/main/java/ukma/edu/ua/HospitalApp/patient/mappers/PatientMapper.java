package ukma.edu.ua.HospitalApp.patient.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.entities.PatientDetails;
import ukma.edu.ua.HospitalApp.patient.dto.PatientDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
  PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

  @Mapping(source = "user.email", target = "email")
  PatientDTO patientToPatientDTO(PatientDetails patient);
}
