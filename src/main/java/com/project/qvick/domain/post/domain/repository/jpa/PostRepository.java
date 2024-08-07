package com.project.qvick.domain.post.domain.repository.jpa;

import com.project.qvick.domain.post.domain.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Transactional(rollbackOn = Exception.class)
    void deleteById(Long id);

}
