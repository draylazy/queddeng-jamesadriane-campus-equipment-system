package edu.cit.queddeng.jamesadriane.campusequipmentloan.Controller;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.User;
import edu.cit.queddeng.jamesadriane.campusequipmentloan.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final Map<String, String> tokenStore = new HashMap<>(); // In-memory token store

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        User user = userService.register(username, password);
        return ResponseEntity.ok("User registered with ID: " + user.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        return userService.authenticate(username, password)
                .map(user -> {
                    String token = UUID.randomUUID().toString(); // simple token
                    tokenStore.put(token, username);
                    return ResponseEntity.ok("Login successful. Token: " + token);
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        if (tokenStore.containsKey(token)) {
            tokenStore.remove(token);
            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.status(401).body("Invalid token");
    }
}