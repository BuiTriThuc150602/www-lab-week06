package vn.edu.iuh.fit.wwwlabweek06.frontend.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.wwwlabweek06.backend.models.User;
import vn.edu.iuh.fit.wwwlabweek06.backend.services.PostServices;
import vn.edu.iuh.fit.wwwlabweek06.backend.services.UserServices;

@org.springframework.stereotype.Controller
public class Controller {

  @Autowired
  private UserServices userServices;
  @Autowired
  private PostServices postServices;

  @GetMapping("/login")
  public String checkLogin(Model model, @RequestParam("email") String email,
      @RequestParam("pwd") String pwd) {
    boolean checkLogin = userServices.checkUser(email, pwd);
    model.addAttribute("checkLogin", checkLogin);
    if (checkLogin) {
      userServices.updateLastLogin(email,
          Instant.now().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
    }
    return checkLogin ? "/dashboard" : "index";
  }

  @GetMapping("/register")
  public String goToRegisterPage() {
    return "users/register";
  }
  @GetMapping("/dashboard")
  public String getPost(Model model, User user){
    model.addAttribute("posts",postServices.getAllPostByUser(user));
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

}
