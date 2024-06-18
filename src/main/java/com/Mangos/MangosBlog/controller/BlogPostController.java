package com.Mangos.MangosBlog.controller;

import com.Mangos.MangosBlog.model.BlogPost;
import com.Mangos.MangosBlog.repository.PostRepository;
import com.Mangos.MangosBlog.services.BlogPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@Slf4j
//@RequestMapping("/api/blogposts") // every method falls under this
@RequiredArgsConstructor //this will generate a constructor for all final fiels
public class BlogPostController {
    //no autowire, because don't want to create instance everytime
    private final PostRepository postRepository;
    private final BlogPostService blogPostService;

    @GetMapping("/api/blogposts")
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/api/blogposts/{id}")
    public BlogPost getBlogPostById(@PathVariable long id) {
        Optional<BlogPost> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        return post.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/write")
    public BlogPost createBlog(@RequestBody BlogPost blogPost) {
        log.debug("Received blog post: {}", blogPost);
        return blogPostService.createPost(blogPost);
    }

    @PutMapping("/api/blogposts/{id}")
    public BlogPost updateBlogPost(@PathVariable long id,
                                   @RequestParam("blogTitle") String blogTitle,
                                   @RequestParam("description") String description,
                                   @RequestParam("content") String content) {
        BlogPost blogPost = new BlogPost();
        blogPost.setBlogTitle(blogTitle);
        blogPost.setDescription(description);
        blogPost.setContent(content);
        return blogPostService.update(blogPost, id);
    }

//    public void updateBlogPost(@PathVariable long id, @RequestBody BlogPost blogPost) {
//        blogPostService.update(blogPost, id);
//    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/blogposts/{id}")
    public void deleteBlogPost(@PathVariable long id) {

        blogPostService.deletePost(id);
    }
/*
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            String fileName = blogPostService.saveFile(file);
            return ResponseEntity.ok(fileName);
        } catch(IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
    */
}



