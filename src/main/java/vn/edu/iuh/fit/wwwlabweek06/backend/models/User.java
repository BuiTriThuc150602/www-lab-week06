package vn.edu.iuh.fit.wwwlabweek06.backend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "first_name", length = 50, nullable = false)
  @NonNull
  private String firstName;
  @Column(name = "middle_name", length = 50, nullable = false)
@NonNull
  private String middleName;
  @Column(name = "last_name", length = 50, nullable = false)
@NonNull
  private String lastName;
  @Column(name = "email", length = 50)
@NonNull
  private String email;
  @Column(name = "mobile", length = 50)
@NonNull
  private String mobile;
  @Lob
  @Column(name = "intro", length = 50)

  private String intro;
  @Lob
  @Column(name = "profile", length = 50)
  private String profile;
  @Column(name = "password_hash", length = 32)
  @NonNull
  private String passwordHash;
  @Column(name = "register_at", length = 6)
  @NonNull
  private Instant registeredAt;
  @Column(name = "last_login", length = 6)

  private Instant lastLogin;
  @OneToMany(mappedBy = "author")
  private Set<Post> posts = new LinkedHashSet<>();
  @OneToMany(mappedBy = "user")
  private Set<PostComment> comments = new LinkedHashSet<>();


}
