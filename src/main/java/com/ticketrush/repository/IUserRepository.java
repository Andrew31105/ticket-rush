package com.ticketrush.repository;

import com.ticketrush.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);
}
