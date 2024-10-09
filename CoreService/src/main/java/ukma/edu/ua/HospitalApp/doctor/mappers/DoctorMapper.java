package ukma.edu.ua.HospitalApp.doctor.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ukma.edu.ua.HospitalApp.common.dto.DoctorDTO;
import ukma.edu.ua.HospitalApp.common.entities.Doctor;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {
  DoctorDTO doctorToDoctorDTO(Doctor doctor);

  Doctor doctorDTOToDoctor(DoctorDTO doctorDTO);
}
