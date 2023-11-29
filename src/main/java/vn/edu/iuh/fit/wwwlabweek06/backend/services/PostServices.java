package vn.edu.iuh.fit.wwwlabweek06.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.Post;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.User;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.PostRepository;

@Service
public class PostServices {
  @Autowired
  private PostRepository postRepository;
  public List<Post> getAllPostByUser(User user){
    return postRepository.findByAuthor_Id(user.getId());
  }

}
