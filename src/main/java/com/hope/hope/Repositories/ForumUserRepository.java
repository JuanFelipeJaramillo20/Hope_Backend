package com.hope.hope.Repositories;

import com.hope.hope.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
