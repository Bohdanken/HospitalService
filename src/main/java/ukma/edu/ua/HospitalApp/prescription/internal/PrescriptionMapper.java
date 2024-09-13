package ukma.edu.ua.HospitalApp.prescription.internal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ukma.edu.ua.HospitalApp.prescription.PrescriptionDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrescriptionMapper {
  PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

  @Mapping(source = "patientDetails.id", target = "patientId")
  PrescriptionDTO prescriptionToPrescriptionDTO(Prescription prescription);
}