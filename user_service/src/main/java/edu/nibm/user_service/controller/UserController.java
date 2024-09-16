package edu.nibm.user_service.controller;

import edu.nibm.user_service.dto.ResponseBody.ResponseBody;
import edu.nibm.user_service.dto.User;
import edu.nibm.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/working")
    public String working() {
        return "User service is working";
    }

    @PostMapping("/register")
    public ResponseBody registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", "User registered successfully");
        responseBody.addUser("user", registeredUser);
        return responseBody;
    }

    @PutMapping("/update")
    public ResponseBody updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", "User updated successfully");
        responseBody.addUser("user", updatedUser);
        return responseBody;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseBody deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", "User deleted successfully");
        return responseBody;
    }

    @PostMapping("/login")
    public ResponseBody loginUser(@RequestParam String username, @RequestParam String password) {
        String loginMessage = userService.loginUser(username, password);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", loginMessage);
        return responseBody;
    }

    @PutMapping("/change-password")
    public ResponseBody changePassword(@RequestParam Long id, @RequestParam String newPassword) {
        String changePasswordMessage = userService.changePassword(id, newPassword);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", changePasswordMessage);
        return responseBody;
    }
}