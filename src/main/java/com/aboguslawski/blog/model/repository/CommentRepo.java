package com.aboguslawski.blog.model.repository;

import com.aboguslawski.blog.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CommentRepo extends JpaRepository<Comment,Long> {
}
