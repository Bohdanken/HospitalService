package ukma.edu.ua.HospitalApp.patient;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
  PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

  @Mapping(source = "user.email", target = "email")
  PatientDTO patientToPatientDTO(PatientDetails patient);
}
