package edu.nibm.user_service.service.IMPL;

import edu.nibm.user_service.dao.UserEntity;
import edu.nibm.user_service.dto.User;
import edu.nibm.user_service.repository.UserRepository;
import edu.nibm.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User registerUser(User userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user = repository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public User updateUser(User userDTO) {
        UserEntity user = repository.findById(userDTO.getId()).orElse(null);
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            repository.save(user);
        }
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public String loginUser(String username, String password) {
        UserEntity user = repository.findByUsernameAndPassword(username, password);
        return user != null ? "Login successful" : "Invalid credentials";
    }

    @Override
    public String changePassword(Long id, String newPassword) {
        UserEntity user = repository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            repository.save(user);
            return "Password changed successfully";
        }
        return "User not found";
    }
}
