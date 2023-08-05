package com.hope.hope.Controllers;

import com.hope.hope.Entities.User;
import com.hope.hope.Services.ForumService;
import com.hope.hope.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hope.hope.DTOs.UserDTO;

@RestController
public class UserController {

    private final UserService userService;
    private final ForumService forumService;

    @Autowired
    public UserController(UserService userService, ForumService forumService) {
        this.userService = userService;
        this.forumService = forumService;
    }

    // ... Other endpoints ...

    @PostMapping("/associate-forum-account")
    public ResponseEntity<String> associateForumAccount(@RequestParam String medicalUsername, @RequestParam String forumUsername) {
        // Retrieve the medical user by medicalUsername
        User medicalUser = userService.findUserByUsername(medicalUsername);
        if (medicalUser == null || !medicalUser.isDoctor() && !medicalUser.isFunctionary()) {
            return ResponseEntity.badRequest().body("Invalid medical user");
        }

        // Retrieve the forum user by forumUsername
        User forumUser = forumService.findUserByUsername(forumUsername);
        if (forumUser == null) {
            return ResponseEntity.badRequest().body("Invalid forum user");
        }

        // Perform the account association by updating the database

        return ResponseEntity.ok("Account associated successfully!");
    }

    @PostMapping("/register/medical")
    public ResponseEntity<String> registerMedicalUser(@RequestBody UserDTO userDTO) {
        // Check if the username already exists
        if (userService.findUserByUsername(userDTO.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }

        userService.registerMedicalUser(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.isDoctor(),
                userDTO.isFunctionary()
        );

        return ResponseEntity.ok("Medical user registered successfully!");
    }

    @PostMapping("/register/forum")
    public ResponseEntity<String> registerForumUser(@RequestBody UserDTO userDTO) {
        // Check if the username already exists
        if (forumService.findUserByUsername(userDTO.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }

        forumService.registerForumUser(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getEmail()
        );

        return ResponseEntity.ok("Forum user registered successfully!");
    }

    @PutMapping("/users/medical/{userId}")
    public ResponseEntity<String> updateMedicalUserInformation(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updateUserInformation(userId, userDTO);
        return ResponseEntity.ok("Medical user information updated successfully!");
    }

    @DeleteMapping("/users/medical/{userId}")
    public ResponseEntity<String> deleteMedicalUser(@PathVariable Long userId) {
        userService.deleteMedicalUser(userId);
        return ResponseEntity.ok("Medical user deleted successfully!");
    }

    @PutMapping("/users/forum/{userId}")
    public ResponseEntity<String> updateForumUserInformation(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        forumService.updateForumUserInformation(userId, userDTO);
        return ResponseEntity.ok("Forum user information updated successfully!");
    }

    @DeleteMapping("/users/forum/{userId}")
    public ResponseEntity<String> deleteForumUser(@PathVariable Long userId) {
        forumService.deleteForumUser(userId);
        return ResponseEntity.ok("Forum user deleted successfully!");
    }
}

