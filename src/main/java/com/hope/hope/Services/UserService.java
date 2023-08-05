package com.hope.hope.Services;

import com.hope.hope.DTOs.UserDTO;
import com.hope.hope.Entities.User;
import com.hope.hope.Repositories.MedicalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MedicalUserRepository medicalUserRepository;

    @Autowired
    public UserService(MedicalUserRepository medicalUserRepository) {
        this.medicalUserRepository = medicalUserRepository;
    }

    public User findUserByUsername(String username) {
        return medicalUserRepository.findByUsername(username);
    }

    public User registerMedicalUser(String username, String password, String email, boolean isDoctor, boolean isFunctionary) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Make sure to properly hash the password before saving
        user.setEmail(email);
        user.setDoctor(isDoctor);
        user.setFunctionary(isFunctionary);
        // Set other user-related fields

        return medicalUserRepository.save(user);
    }

    public User updateUserInformation(Long userId, UserDTO userDTO) {
        // Retrieve the user by userId
        User user = medicalUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user information
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // Update other fields as needed

        return medicalUserRepository.save(user);
    }

    public void deleteMedicalUser(Long userId) {
        // Retrieve the user by userId
        User user = medicalUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        medicalUserRepository.delete(user);
    }
}
