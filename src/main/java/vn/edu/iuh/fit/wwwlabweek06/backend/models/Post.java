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
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "title", length = 75)
  private String title;
  @Column(name = "published")
  private Boolean published;
  @Lob
  @Column(name = "content", nullable = false)
  private String content;
  @Column(name = "meta_title", length = 100)
  private String metaTitle;
  @Lob
  @Column(name = "summary")
  private String summary;
  @Column(name = "create_at", length = 6)
  private Instant createAt;
  @Column(name = "published_at", length = 6)

  private Instant publishedAt;
  @Column(name = "update_at", length = 6)

  private Instant updateAt;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "author_id")
  private User author;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "parent_id")
  private Post parent;
  @OneToMany(mappedBy = "post")
  private Set<PostComment> postComments = new LinkedHashSet<>();
  @OneToMany(mappedBy = "parent")
  private Set<Post> posts = new LinkedHashSet<>();


}
