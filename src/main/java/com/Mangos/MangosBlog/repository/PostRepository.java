package com.Mangos.MangosBlog.repository;

import com.Mangos.MangosBlog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<BlogPost, Long> {
        List<BlogPost> findByBlogId(Long id);
        List<BlogPost> findByBlogTitle(String blogTitle);
       // List<BlogPost> findAllByBlogTitleContains(String key);
        //List<BlogPost> findAllByContentContains(String keywords);
}
