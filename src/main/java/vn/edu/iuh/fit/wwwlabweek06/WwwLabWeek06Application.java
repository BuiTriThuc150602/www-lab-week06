package vn.edu.iuh.fit.wwwlabweek06;

import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.Post;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.PostComment;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.User;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.PostRepository;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.UserRepository;
import vn.edu.iuh.fit.wwwlabweek06.backend.services.UserServices;

@SpringBootApplication
public class WwwLabWeek06Application {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostCommentRepository postCommentRepository;


  public static void main(String[] args) {
    SpringApplication.run(WwwLabWeek06Application.class, args);
  }

//  @Bean
  CommandLineRunner runner() {
    return args -> {
      User thuc = new User("Bui", "Tri", "Thuc", "trithucbuittb@gmail.com", "0963015348",
          "thuc123", Instant.now());
      userRepository.save(thuc);
      for (int i = 0; i < 4; i++) {
        Post post = new Post("Post Testing " + i, true,
            "Try the AI text generator, a tool for content creation. It leverages a transformer-based Large Language Model (LLM) to produce text that follows the users instructions. As an AI generator, it offers a range of functions, from text generation, to completing sentences, and predicting contextually relevant content. It can serve as a sentence generator, word generator, and message generator, transforming input into coherent text.",
            "Meta Title " + i, "summary of post test " + i,
            Instant.now(), Instant.now(), thuc);
        postRepository.save(post);
        User user = new User("Join", "Smith", "Hand " + i, "email" + i + "@user.com",
            "012345678",
            "password" + i, Instant.now());
        userRepository.save(user);

        PostComment postComment = new PostComment("Title comment" + i, true, Instant.now(),
            Instant.now(), "This the good post, thanks your post",
            user, post);
        postCommentRepository.save(postComment);

      }


    };
  }

}
