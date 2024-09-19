package ukma.edu.ua.HospitalApp.doctor.internal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ukma.edu.ua.HospitalApp.doctor.DoctorDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {

  // Mapping from entity to DTO
  @Mapping(source = "user.email", target = "email")
  DoctorDTO doctorToDoctorDTO(DoctorDetails doctor);

  // Mapping from DTO to entity
  @Mapping(target = "user.email", source = "email")
  DoctorDetails doctorDTOToDoctor(DoctorDTO doctorDTO);
}
