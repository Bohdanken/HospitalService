package ukma.edu.ua.HospitalApp.hospital.internal;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ukma.edu.ua.HospitalApp.entities.internal.Hospital;
import ukma.edu.ua.HospitalApp.entities.HospitalDTO;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    // Convert Hospital entity to HospitalDTO
    HospitalDTO hospitalToHospitalDTO(Hospital hospital);

    // Convert HospitalDTO to Hospital entity
    Hospital hospitalDTOToHospital(HospitalDTO hospitalDTO);
}
