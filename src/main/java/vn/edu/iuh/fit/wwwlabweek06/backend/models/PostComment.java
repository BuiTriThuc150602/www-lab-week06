package vn.edu.iuh.fit.wwwlabweek06.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_comment")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PostComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "title", length = 100)
  @NonNull
  private String title;
  @Column(name = "published")
  @NonNull
  private Boolean published;
  @Column(name = "published_at", length = 6)
  @NonNull
  private Instant publishedAt;
  @Column(name = "create_at", length = 6)
  @NonNull

  private Instant createAt;
  @Lob
  @Column(name = "content")
  @NonNull
  private String content;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @NonNull
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @NonNull
  @JoinColumn(name = "post_id")
  private Post post;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private PostComment parent;
  @OneToMany(mappedBy = "parent")
  private Set<PostComment> postComments;
}
