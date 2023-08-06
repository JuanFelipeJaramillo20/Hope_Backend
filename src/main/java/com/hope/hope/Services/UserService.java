package com.hope.hope.Services;

import com.hope.hope.DTOs.UserDTO;
import com.hope.hope.Entities.User;
import com.hope.hope.Repositories.MedicalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    private final MedicalUserRepository medicalUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(MedicalUserRepository medicalUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.medicalUserRepository = medicalUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByUsername(String username) {
        return medicalUserRepository.findByUsername(username);
    }

    public User registerMedicalUser(String username, String password, String email, boolean isDoctor, boolean isFunctionary) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
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
