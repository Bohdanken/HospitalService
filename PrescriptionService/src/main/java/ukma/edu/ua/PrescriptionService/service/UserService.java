package ukma.edu.ua.PrescriptionService.service;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.proto.User.UserId;
import ukma.edu.ua.proto.User.UserResponse;
import ukma.edu.ua.proto.UserServiceGrpc.UserServiceBlockingStub;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.grpc.StatusRuntimeException;
import io.grpc.Status.Code;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserServiceBlockingStub userService;

  public UserResponse getUserData(Long id) {
    try {
      return userService.getById(UserId.newBuilder().setId(id).build());
    } catch (StatusRuntimeException e) {
      if (e.getStatus().getCode().compareTo(Code.NOT_FOUND) == 0) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      }
      throw e;
    }
  }
}
