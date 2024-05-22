package com.Mangos.MangosBlog.repository;

import com.Mangos.MangosBlog.model.BlogComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<BlogComments, Long> {
    List<BlogComments> findByBlogId(Long id);
    List<BlogComments> findByCommentUsername(String commentUsername);
    List<BlogComments> deleteByBlogId(Long id);
    List<BlogComments> findByBlogIdAndCommentUsername(Long id, String username);
    List<BlogComments> deleteByBlogIdAndCommentUsername(Long id, String username);

}


