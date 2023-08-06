package com.hope.hope.Services;

import com.hope.hope.DTOs.UserDTO;
import com.hope.hope.Entities.User;
import com.hope.hope.Repositories.ForumUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class ForumService {
    private final ForumUserRepository forumUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ForumService(ForumUserRepository forumUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.forumUserRepository = forumUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByUsername(String username) {
        return forumUserRepository.findByUsername(username);
    }

    public User registerForumUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setDoctor(false);
        user.setFunctionary(false);
        // Set other user-related fields

        return forumUserRepository.save(user);
    }

    public User updateForumUserInformation(Long userId, UserDTO userDTO) {
        // Retrieve the user by userId
        User user = forumUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user information
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // Update other fields as needed

        return forumUserRepository.save(user);
    }

    public void deleteForumUser(Long userId) {
        // Retrieve the user by userId
        User user = forumUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        forumUserRepository.delete(user);
    }
}
