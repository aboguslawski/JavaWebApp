package com.aboguslawski.blog.model.repository;

import com.aboguslawski.blog.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TagRepo extends JpaRepository<Tag, Long> {
}
