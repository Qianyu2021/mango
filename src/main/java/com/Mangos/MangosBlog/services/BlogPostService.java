package com.Mangos.MangosBlog.services;

import com.Mangos.MangosBlog.model.BlogPost;
import com.Mangos.MangosBlog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final PostRepository postRepository;
    //private static final String upload_dir = "upload/";
    private final Path root = Paths.get("upload");

    public Optional<BlogPost> getById(Long id){

        return postRepository.findById(id);
    }


    public List<BlogPost> getAllPosts() {

        return postRepository.findAll();
    }
    public BlogPost createPost(BlogPost blogPost) {
        blogPost.setCreatedDate(LocalDate.now());
        return postRepository.save(blogPost);
    }

    public BlogPost update(BlogPost blogPost, Long id) {
        return postRepository.findById(id).map(existingPost -> {
            if (blogPost.getBlogTitle() != null) {
                existingPost.setBlogTitle(blogPost.getBlogTitle());
            }
            if (blogPost.getDescription() != null) {
                existingPost.setDescription(blogPost.getDescription());
            }
            if (blogPost.getContent() != null) {
                existingPost.setContent(blogPost.getContent());
            }
            existingPost.setUpdatedDate(LocalDate.now());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    public void deletePost(long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        postRepository.delete(post);
    }




}
