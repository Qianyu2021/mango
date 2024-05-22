package com.Mangos.MangosBlog.controller;

import com.Mangos.MangosBlog.model.BlogComments;
import com.Mangos.MangosBlog.services.BlogPostService;
import com.Mangos.MangosBlog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/blogposts/{blogId}/comments")
public class CommentController {

    private final BlogPostService blogPostService;
    private final CommentService commentService;

   @Autowired
   public CommentController(CommentService commentService, BlogPostService blogPostService){
       this.blogPostService = blogPostService;
       this.commentService = commentService;
   }
    //post a new comment under a specific blog
    @PostMapping("")
    public ResponseEntity<?> createComment(@PathVariable Long blogId, @RequestBody @Valid BlogComments comment){

        //save the new comment
        BlogComments newComment = commentService.createComment(comment, blogId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getCommentsByBlogId(@PathVariable Long blogId){
       try{
           List<BlogComments> comments = commentService.getCommentByBlogId(blogId);
           return new ResponseEntity<>(comments, HttpStatus.OK);
       } catch (ResponseStatusException e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "blogId") Long blogId, @PathVariable(value = "commentId") Long commentId) {
        try {
            commentService.deleteComment(blogId, commentId);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

}
