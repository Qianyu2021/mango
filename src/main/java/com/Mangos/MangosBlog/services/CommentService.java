package com.Mangos.MangosBlog.services;

import com.Mangos.MangosBlog.model.BlogComments;
import com.Mangos.MangosBlog.model.BlogPost;
import com.Mangos.MangosBlog.repository.CommentRepository;
import com.Mangos.MangosBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogPostService blogPostService;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BlogPostService blogPostService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.blogPostService = blogPostService;
        this.postRepository = postRepository;
    }

    public BlogComments createComment(BlogComments comment, Long blogId) {

        return blogPostService.getById(blogId).map(blogPost -> {
            comment.setBlogId(blogId);
            comment.setCreatedAt(LocalDateTime.now());
            comment.setCommentUsername(comment.getCommentUsername() == null || comment.getCommentUsername().isEmpty() ? "anonymous" : comment.getCommentUsername());
            return commentRepository.save(comment);
        }).orElseThrow(()-> new RuntimeException("Blog post not found with id: " + blogId));
    }

    public List<BlogComments> getCommentByBlogId(Long blogId) {
        //check if the blog exists
        if(!blogPostService.getById(blogId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "blog not found with id: " + blogId);
        }
        List<BlogComments> comments = commentRepository.findByBlogId(blogId);
        if(comments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is no comments");
        }
        return comments;
    }

    public List<BlogComments> getCommentByCommentUsername(String commentUsername) {
        List<BlogComments> comments = commentRepository.findByCommentUsername(commentUsername);
        if (comments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is no comments");
        }
        return comments;
    }


    public void deleteComment(Long blogId,Long commentId ) {
        BlogPost post = blogPostService.getById(blogId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post not found with id: " + blogId));

        BlogComments comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with id: " + commentId));

        if (!comment.getBlogId().equals(blogId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Comment does not belong to the given blog post");
        }
        commentRepository.deleteByBlogId(commentId);
    }
}
