package com.cats.catsforcuteness.Controller;

import com.cats.catsforcuteness.DTO.UserDTO;
import com.cats.catsforcuteness.Entity.User;
import com.cats.catsforcuteness.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cFc/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "User already exists"));
        } else {
            User registeredUser = userService.registerUser(user);
            UserDTO userDTO = new UserDTO(registeredUser.getFullName(), registeredUser.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User registered successfully", "user", userDTO));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Optional<User> user = userService.loginUser(email, password);
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO(user.get().getFullName(), user.get().getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User logged in successfully", "user", userDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid email or password"));
        }
    }

}
