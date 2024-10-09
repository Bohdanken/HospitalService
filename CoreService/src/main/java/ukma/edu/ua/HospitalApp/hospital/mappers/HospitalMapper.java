package ukma.edu.ua.HospitalApp.hospital.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.common.dto.HospitalDTO;
import ukma.edu.ua.HospitalApp.common.entities.Hospital;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    HospitalDTO hospitalToHospitalDTO(Hospital hospital);

    Hospital hospitalDTOToHospital(HospitalDTO hospitalDTO);
}
