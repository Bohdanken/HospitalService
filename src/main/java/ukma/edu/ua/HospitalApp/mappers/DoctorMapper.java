package ukma.edu.ua.HospitalApp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ukma.edu.ua.HospitalApp.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.models.DoctorDetails;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {
  DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

  @Mapping(source = "user.email", target = "email")
  DoctorDTO doctorToDoctorDTO(DoctorDetails doctor);
}
