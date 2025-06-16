package com.transparency.repository;
import java.util.Optional;
import com.transparency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // custom method to find by email
}
