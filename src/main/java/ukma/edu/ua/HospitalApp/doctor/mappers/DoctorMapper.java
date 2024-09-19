package ukma.edu.ua.HospitalApp.doctor.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ukma.edu.ua.HospitalApp.entities.DoctorDTO;
import ukma.edu.ua.HospitalApp.entities.internal.DoctorDetails;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)  // Ensure the component model is set for Spring
public interface DoctorMapper {

  // Map DoctorDetails entity to DoctorDTO
  @Mapping(source = "user.email", target = "email")
  DoctorDTO doctorToDoctorDTO(DoctorDetails doctor);

  // Map DoctorDTO back to DoctorDetails entity
  DoctorDetails doctorDTOToDoctor(DoctorDTO doctorDTO);
}
