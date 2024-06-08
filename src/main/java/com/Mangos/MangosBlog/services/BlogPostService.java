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
    //private static final String upload_dir = "upload/";

    public Optional<BlogPost> getById(Long id){
        return postRepository.findById(id);
    }


    public List<BlogPost> getAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(BlogPost blogPost)  {
       // String imgPath = saveImage(img);
        postRepository.save(blogPost);
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

   /* private String saveImage(MultipartFile img) throws IOException {
        String originalFilename = img.getOriginalFilename();
        String filePath = upload_dir + originalFilename;
        File dir = new File(filePath);
        if (img.isEmpty()) {
            return null;
        }
        String fileName = img.getOriginalFilename();
        Path path = Paths.get(upload_dir + fileName);
        img.transferTo(new File(path.toString()));
        return path.toString();
    } */
}
