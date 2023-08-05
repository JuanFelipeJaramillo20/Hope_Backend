package com.hope.hope.Services;

import com.hope.hope.DTOs.UserDTO;
import com.hope.hope.Entities.User;
import com.hope.hope.Repositories.ForumUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumService {
    private final ForumUserRepository forumUserRepository;

    @Autowired
    public ForumService(ForumUserRepository forumUserRepository) {
        this.forumUserRepository = forumUserRepository;
    }

    public User findUserByUsername(String username) {
        return forumUserRepository.findByUsername(username);
    }

    public User registerForumUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Make sure to properly hash the password before saving
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
