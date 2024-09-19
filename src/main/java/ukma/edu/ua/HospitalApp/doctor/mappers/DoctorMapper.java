package ukma.edu.ua.HospitalApp.doctor.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.doctor.DoctorDTO;
import ukma.edu.ua.HospitalApp.entities.DoctorDetails;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {
  DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

  @Mapping(source = "user.email", target = "email")
  DoctorDTO doctorToDoctorDTO(DoctorDetails doctor);
}
