package edu.nibm.user_service.service;

import edu.nibm.user_service.dto.User;

public interface UserService {
    User registerUser(User userDTO);
    User updateUser(User userDTO);
    void deleteUser(Long id);
    String loginUser(String username, String password);
    String changePassword(Long id, String newPassword);
}
