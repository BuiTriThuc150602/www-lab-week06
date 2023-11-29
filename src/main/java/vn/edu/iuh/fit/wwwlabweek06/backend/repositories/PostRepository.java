package vn.edu.iuh.fit.wwwlabweek06.backend.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findByAuthor_Id(long id);

}