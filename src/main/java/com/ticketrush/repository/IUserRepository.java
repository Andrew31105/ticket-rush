package com.ticketrush.repository;

import com.ticketrush.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    // Dùng Optional để bắt trường hợp không tìm thấy user một cách an toàn
    Optional<User> findByUsername(String username);
}
