package ukma.edu.ua.HospitalApp.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.edu.ua.HospitalApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByEmail(String email);
}

