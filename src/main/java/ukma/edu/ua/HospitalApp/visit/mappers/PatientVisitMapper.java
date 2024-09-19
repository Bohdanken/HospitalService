package ukma.edu.ua.HospitalApp.visit.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.entities.PatientVisit;
import ukma.edu.ua.HospitalApp.visit.dto.VisitDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientVisitMapper {

    PatientVisitMapper INSTANCE = Mappers.getMapper(PatientVisitMapper.class);

    // @Mapping(source = "patientDetailsId", target = "patientId")
    // @Mapping(source = "doctorDetailsId", target = "doctorId")
    @Mapping(source = "patientDetails.id", target = "patientId")
    @Mapping(source = "doctorDetails.id", target = "doctorId")
    VisitDTO patientVisitToVisitDTO(PatientVisit patientVisit);
}
