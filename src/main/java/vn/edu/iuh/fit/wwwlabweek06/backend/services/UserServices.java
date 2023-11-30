package vn.edu.iuh.fit.wwwlabweek06.backend.services;

import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.User;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.UserRepository;

@Service
public class UserServices {

  @Autowired
  private UserRepository userRepository;

  public boolean checkUser(String email, String password) {
    return userRepository.existsByEmailAndPasswordHash(email, password);
  }

  public Optional<User> findUserByLogin(String email, String pwd) {
    return userRepository.findByEmailAndPasswordHash(email, pwd);
  }

  public User AddUser(User user) {
    return userRepository.save(user);
  }

  public void updateLastLogin(String email, Instant lastLogin) {
    userRepository.updateLastLoginByEmail(lastLogin, email);
  }

}
