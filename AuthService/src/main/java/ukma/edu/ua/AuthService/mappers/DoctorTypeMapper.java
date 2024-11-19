package ukma.edu.ua.AuthService.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.AuthService.entities.Doctor.DoctorType;

@Mapper(componentModel = "spring")
public interface DoctorTypeMapper {
	DoctorTypeMapper INSTANCE = Mappers.getMapper(DoctorTypeMapper.class);

	// Map from EntityUserRole to ProtoUserRole
	ukma.edu.ua.proto.User.DoctorType doctorTypeToProto(DoctorType role);
}
