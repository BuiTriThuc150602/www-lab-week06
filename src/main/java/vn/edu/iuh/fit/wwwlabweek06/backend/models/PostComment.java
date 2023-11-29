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
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "post_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class PostComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "title", length = 100)
  private String title;
  @Column(name = "published")
  private Boolean published;
  @Column(name = "published_at", length = 6)
  private Instant publishedAt;
  @Column(name = "create_at", length = 6)

  private Instant createAt;
  @Lob
  @Column(name = "content")
  private String content;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "post_id")
  private Post post;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "parent_id")
  private PostComment parent;
  @OneToMany(mappedBy = "parent")
  private Set<PostComment> postComments;
}
