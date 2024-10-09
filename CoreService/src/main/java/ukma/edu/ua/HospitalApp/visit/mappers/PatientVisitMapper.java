package ukma.edu.ua.HospitalApp.visit.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.common.dto.VisitDTO;
import ukma.edu.ua.HospitalApp.common.entities.PatientVisit;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientVisitMapper {

    PatientVisitMapper INSTANCE = Mappers.getMapper(PatientVisitMapper.class);

    @Mapping(source = "patientDetails.id", target = "patientId")
    @Mapping(source = "doctorDetails.id", target = "doctorId")
    VisitDTO patientVisitToVisitDTO(PatientVisit patientVisit);
}
