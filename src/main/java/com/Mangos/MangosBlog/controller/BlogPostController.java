package com.Mangos.MangosBlog.controller;

import com.Mangos.MangosBlog.model.BlogPost;
import com.Mangos.MangosBlog.repository.PostRepository;
import com.Mangos.MangosBlog.services.BlogPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/blogposts") // every method falls under this
@RequiredArgsConstructor //this will generate a constructor for all final fiels
public class BlogPostController {
    //no autowire, because don't want to create instance everytime
    private final PostRepository postRepository;
    private final BlogPostService blogPostService;

    @GetMapping("")
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable long id) {
        Optional<BlogPost> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        return post.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/write")
    public BlogPost createBlog(@RequestBody BlogPost blogPost) {
        log.debug("Received blog post: {}", blogPost);
        return blogPostService.createPost(blogPost);
    }

    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable long id) {

        blogPostService.deletePost(id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/{id}/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public URL uploadBlogPicture(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        System.out.println("got here");
        return blogPostService.uploadPicture(id, file);
    }
}



