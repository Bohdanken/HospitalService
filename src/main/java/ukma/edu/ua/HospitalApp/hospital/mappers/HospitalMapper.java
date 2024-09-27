package ukma.edu.ua.HospitalApp.hospital.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ukma.edu.ua.HospitalApp.entities.HospitalDTO;
import ukma.edu.ua.HospitalApp.entities.internal.Hospital;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HospitalMapper {
    HospitalDTO hospitalToHospitalDTO(Hospital hospital);

    Hospital hospitalDTOToHospital(HospitalDTO hospitalDTO);
}
