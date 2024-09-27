package ukma.edu.ua.HospitalApp.prescription.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.entities.src.main.java.internal.Prescription;
import ukma.edu.ua.HospitalApp.entities.src.main.java.dto.PrescriptionDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrescriptionMapper {
  PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

  @Mapping(source = "patientDetails.id", target = "patientId")
  PrescriptionDTO prescriptionToPrescriptionDTO(Prescription prescription);
}
