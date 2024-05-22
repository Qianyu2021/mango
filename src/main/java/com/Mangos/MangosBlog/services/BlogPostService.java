package com.Mangos.MangosBlog.services;

import com.Mangos.MangosBlog.model.BlogPost;
import com.Mangos.MangosBlog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final PostRepository postRepository;

    public Optional<BlogPost> getById(Long id){
        return postRepository.findById(id);
    }

    public boolean existsById(Long id){
        return postRepository.existsById(id);
    }

    public List<BlogPost> getAllPosts() {
        return postRepository.findAll();
    }

    public BlogPost createPost(BlogPost post) {
        if(post.getBlogId() != null) post.setCreatedDate(LocalDate.now());
        return postRepository.save(post);
    }

    public BlogPost update(BlogPost blogPost, Long id) {
        return postRepository.findById(id).map(existingPost -> {
            if (blogPost.getBlogTitle() != null) {
                existingPost.setBlogTitle(blogPost.getBlogTitle());
            }
            existingPost.setUpdatedDate(LocalDate.now());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }
    public void deletePost(long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id"));
        postRepository.delete(post);
    }
}
