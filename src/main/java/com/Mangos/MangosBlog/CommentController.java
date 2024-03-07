package com.Mangos.MangosBlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    //post a new comment
    @PostMapping("/post")
    public BlogComment postComment(@RequestBody BlogComment comment){
        return commentRepository.save(comment);
    }
    // get all comments
    @GetMapping("/all")
    public List<BlogComment> getAllComments(){
        return commentRepository.findAll();
    }
}
