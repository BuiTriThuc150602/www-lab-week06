package vn.edu.iuh.fit.wwwlabweek06.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.wwwlabweek06.backend.repositories.PostCommentRepository;

@Service
public class PostCommentServices {

  @Autowired
  private PostCommentRepository postCommentRepository;

}
