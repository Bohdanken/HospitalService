package ukma.edu.ua.VisitService.config;

import org.springframework.context.annotation.Configuration;

import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.inject.GrpcClientBean;
import ukma.edu.ua.proto.UserServiceGrpc.UserServiceBlockingStub;

@Configuration
@GrpcClientBean(clazz = UserServiceBlockingStub.class, beanName = "authStub", client = @GrpcClient("auth"))
public class AuthServiceConfig {
}