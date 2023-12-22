package com.project.qvick.domain.user.domain.repository;

import com.project.qvick.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
