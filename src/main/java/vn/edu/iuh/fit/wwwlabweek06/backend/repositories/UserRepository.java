package vn.edu.iuh.fit.wwwlabweek06.backend.repositories;

import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);

  @Transactional
  @Modifying
  @Query("update User u set u.lastLogin = ?1 where u.email = ?2")
  void updateLastLoginByEmail(Instant lastLogin, String email);
  boolean existsByEmailAndPasswordHash(
      String email, String passwordHash);

}