package com.hope.hope.Repositories;

import com.hope.hope.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
