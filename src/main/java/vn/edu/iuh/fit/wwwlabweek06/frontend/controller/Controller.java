package vn.edu.iuh.fit.wwwlabweek06.frontend.controller;

import jakarta.servlet.http.HttpSession;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.Post;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.PostComment;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.User;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.PostRepository;
import vn.edu.iuh.fit.wwwlabweek06.backend.services.PostServices;
import vn.edu.iuh.fit.wwwlabweek06.backend.services.UserServices;

@org.springframework.stereotype.Controller
public class Controller {

  @Autowired
  private UserServices userServices;
  @Autowired
  private PostServices postServices;
  @Autowired
  private PostCommentRepository postCommentRepository;
  @Autowired
  private PostRepository postRepository;

  @GetMapping("/login")
  public String checkLogin(Model model, @RequestParam("email") String email,
      @RequestParam("pwd") String pwd, HttpSession session) {
    boolean checkLogin = userServices.checkUser(email, pwd);
    model.addAttribute("checkLogin", checkLogin);
    if (checkLogin) {
      userServices.updateLastLogin(email,
          Instant.now().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
      Optional<User> user = userServices.findUserByLogin(email, pwd);
      System.out.println(user);
      user.ifPresent(userLogin -> {
        session.setAttribute("userLogin", userLogin);
      });
    }
    return checkLogin ? "redirect:/dashboard" : "index";
  }

  @GetMapping("/register")
  public String goToRegisterPage() {
    return "users/register";
  }

  @GetMapping("/dashboard")
  public String getPost(Model model, HttpSession session) {
    User user = (User) session.getAttribute("userLogin");
    if (user == null) {
      return "index";
    }
    model.addAttribute("userLogin", user);
    model.addAttribute("posts", postServices.getAllPostByUser(user));
    return "dashboard";
  }

  @PostMapping("/register/user")
  public String register(Model model, @RequestParam("firstName") String fName,
      @RequestParam("middleName") Optional<String> midName, @RequestParam("lastName") String lName,
      @RequestParam("phone") String mobile, @RequestParam("email") String email,
      @RequestParam("pwd") String pwd) {
    Instant registerAt = Instant.now();
    model.addAttribute("newUser", userServices.AddUser(
        new User(fName, midName.orElse(""), lName, email, mobile, pwd, registerAt)));
    return "index";
  }

  @GetMapping("/about")
  public String about(HttpSession session, Model model) {
    User user = (User) session.getAttribute("userLogin");
    if (user == null) {
      return "index";
    }
    model.addAttribute("userLogin", user);
    return "users/about";
  }

  @GetMapping("/contact")
  public String contact(HttpSession session, Model model) {
    User user = (User) session.getAttribute("userLogin");
    if (user == null) {
      return "index";
    }
    model.addAttribute("userLogin", user);
    return "users/contact";
  }

  @PostMapping("/addComment")
  public String addComment(
      @RequestParam("post") Optional<Long> postId,
      @RequestParam("parent") Optional<Long> parentId,
      @RequestParam("commentContent") Optional<String> commentContent,
      HttpSession session) {
    User author = (User) session.getAttribute("userLogin");
    if (postId.isPresent() && commentContent.isPresent()) {
      Optional<Post> post = postRepository.findById(postId.get());
      PostComment postComment = new PostComment("", true, Instant.now(),
          Instant.now(), commentContent.get(),
          author, post.get());
      parentId.flatMap(id -> postCommentRepository.findById(id)).ifPresent(postComment::setParent);
      postCommentRepository.save(postComment);
    }

    return "redirect:/dashboard";
  }
  @GetMapping("/posting")
  public  String goToPost(){
    return "posts/post";
  }

  @PostMapping("/createPost")
  public String createPost(@RequestParam("title") String title, @RequestParam("metaTitle") String metaTitle, @RequestParam("content") String content,@RequestParam("publish") boolean publish,@RequestParam("summary") String summary, HttpSession session){
    User user = (User) session.getAttribute("userLogin");
    if (user == null) {
      return "index";
    }
    Post post = new Post(title, publish,
        content,
        metaTitle, summary,
        Instant.now(), Instant.now(), user);
    postRepository.save(post);
    return "redirect:/dashboard";
  }

}
