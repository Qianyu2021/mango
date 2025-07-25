package com.Mangos.MangosBlog.services;

import com.Mangos.MangosBlog.model.BlogPost;
import com.Mangos.MangosBlog.repository.PostRepository;
import com.Mangos.MangosBlog.s3.S3Buckets;
import com.Mangos.MangosBlog.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final PostRepository postRepository;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public Optional<BlogPost> getById(Long id) {

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


    public URL uploadPicture(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty");
        }
        String fileName = file.getOriginalFilename();
        String fileId = UUID.randomUUID().toString();
        try {
            // Upload file to S3
            s3Service.uploadFile(
                    s3Buckets.getBlog(),
                    "post-images/temp/%s".formatted(fileId),
                    file.getBytes()
            );
            URL result = s3Service.getimageUrl(
                    s3Buckets.getBlog(),
                    "post-images/temp/%s".formatted(fileId));
            log.info(result.toString());

            return result;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

}






