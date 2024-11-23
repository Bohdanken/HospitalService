package ukma.edu.ua.AuthService.grpc;

import com.google.protobuf.Timestamp;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ukma.edu.ua.AuthService.repositories.UserRepository;
import ukma.edu.ua.proto.User.AdminDetails;
import ukma.edu.ua.proto.User.DoctorDetails;
import ukma.edu.ua.proto.User.PatientDetails;
import ukma.edu.ua.proto.User.UserId;
import ukma.edu.ua.proto.User.UserResponse;
import ukma.edu.ua.proto.UserServiceGrpc.UserServiceImplBase;
import ukma.edu.ua.AuthService.entities.Admin;
import ukma.edu.ua.AuthService.entities.Doctor;
import ukma.edu.ua.AuthService.entities.Patient;
import ukma.edu.ua.AuthService.mappers.DoctorTypeMapper;
import ukma.edu.ua.AuthService.mappers.UserRoleMapper;

@GrpcService
@RequiredArgsConstructor
public class GrpcUserService extends UserServiceImplBase {
	private final UserRepository userRepository;

	@Override
	public void getById(UserId request, StreamObserver<UserResponse> responseObserver) {
		var userId = request.getId();
		var user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			responseObserver.onError(new StatusRuntimeException(Status.NOT_FOUND.withDescription("User not found")));
			return;
		}

		var userResponse = UserResponse.newBuilder()
				.setId(userId)
				.setEmail(user.getEmail())
				.setPassword(user.getPassword())
				.setRole(UserRoleMapper.INSTANCE.entityRoleToProtoRole(user.getRole()));

		switch (user.getRole()) {
			case ADMIN: {
				var admin = (Admin) user;
				userResponse.setAdminDetails(
						AdminDetails.newBuilder()
								.setFirstName(admin.getFirstName())
								.setLastName(admin.getLastName())
								.build());
				break;
			}
			case DOCTOR: {
				var doctor = (Doctor) user;
				userResponse.setDoctorDetails(
						DoctorDetails.newBuilder()
								.setFirstName(doctor.getFirstName())
								.setLastName(doctor.getLastName())
								.setDoctorType(DoctorTypeMapper.INSTANCE.doctorTypeToProto(doctor.getDoctorType()))
								.setBirthDate(Timestamp.newBuilder()
										.setSeconds(doctor.getBirthDate().toInstant().getEpochSecond())
										.setNanos(doctor.getBirthDate().toInstant().getNano())
										.build())
								.build());
				break;
			}
			case PATIENT: {
				var patient = (Patient) user;
				userResponse.setPatientDetails(
						PatientDetails.newBuilder()
								.setFirstName(patient.getFirstName())
								.setLastName(patient.getLastName())
								.setPassportNumber(patient.getPassportNumber())
								.setAddress(patient.getAddress())
								.build());
				break;
			}
			default:
				break;
		}

		responseObserver.onNext(userResponse.build());
		responseObserver.onCompleted();
	}
}
