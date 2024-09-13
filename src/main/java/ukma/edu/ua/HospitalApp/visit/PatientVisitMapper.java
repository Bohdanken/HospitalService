package ukma.edu.ua.HospitalApp.visit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientVisitMapper {

    PatientVisitMapper INSTANCE = Mappers.getMapper(PatientVisitMapper.class);

//    @Mapping(source = "patientDetailsId", target = "patientId")
//    @Mapping(source = "doctorDetailsId", target = "doctorId")
    @Mapping(source = "patientDetails.id", target = "patientId")
    @Mapping(source = "doctorDetails.id", target = "doctorId")
    VisitDTO patientVisitToVisitDTO(PatientVisit patientVisit);
}