package ukma.edu.ua.AuthService.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ukma.edu.ua.AuthService.entities.User.Role;
import ukma.edu.ua.proto.User.UserRole;

@Mapper
public interface UserRoleMapper {
	UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

	// Map from EntityUserRole to ProtoUserRole
	UserRole entityRoleToProtoRole(Role role);
}
