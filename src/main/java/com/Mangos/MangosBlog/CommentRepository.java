package com.Mangos.MangosBlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<BlogComment, Long> {
}
