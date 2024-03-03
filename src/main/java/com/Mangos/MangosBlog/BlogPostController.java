package com.Mangos.MangosBlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    // Get a single blog post by ID
    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable Long id) {
       return blogPostService.getBlogPostById(id).orElseThrow(()-> new RuntimeException("Post is not found"));
    }

    // Create a new blog post
    @PostMapping
    public BlogPost createBlogPost (@RequestBody BlogPost blogPost){
        return blogPostService.saveOrUpdateBlogPost(blogPost);
    }

    // Update an existing blog post
    @PutMapping("/{id}")
    public BlogPost updateBlogPost (@PathVariable Long id, @RequestBody BlogPost blogPostDetails){
    return blogPostService.getBlogPostById(id).map(blogPost->{
        blogPost.setTitle(blogPostDetails.getTitle());
        blogPost.setContent(blogPostDetails.getContent());
        //update other fields as necessary
        return blogPostService.saveOrUpdateBlogPost(blogPost);
        }).orElseThrow(()-> new RuntimeException("BlogPost not found"));
    }

    // Delete a blog post
    @DeleteMapping("/{id}")
    public void deleteBlogPost (@PathVariable Long id){
        blogPostService.deleteBlogPost(id);
    }
}

