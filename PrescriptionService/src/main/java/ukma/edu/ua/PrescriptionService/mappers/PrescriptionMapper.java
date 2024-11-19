package ukma.edu.ua.PrescriptionService.mappers;

import java.nio.charset.StandardCharsets;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.PrescriptionService.dto.PrescriptionDTO;
import ukma.edu.ua.PrescriptionService.model.Prescription;

@Mapper
public interface PrescriptionMapper {
	PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

	public PrescriptionDTO prescriptionToPrescriptionDto(Prescription car);

	default String bytesToString(byte[] bytes) {
		return bytes != null ? new String(bytes, StandardCharsets.UTF_8) : null;
	}
}
