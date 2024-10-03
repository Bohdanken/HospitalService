package ukma.edu.ua.HospitalApp.prescription.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.HospitalApp.common.dto.PrescriptionDTO;
import ukma.edu.ua.HospitalApp.common.entities.Prescription;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrescriptionMapper {
  PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

  @Mapping(source = "patient.id", target = "patientId")
  PrescriptionDTO prescriptionToPrescriptionDTO(Prescription prescription);
}
