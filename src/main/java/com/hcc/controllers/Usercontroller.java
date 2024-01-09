package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.services.AuthorityService;
import com.hcc.utils.CustomPasswordEncoder;
import com.hcc.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class Usercontroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private UserRepository userRepo;

    //TODO: User login mapping
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialRequest authCredentialRequest) throws Exception {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authCredentialRequest.getUsername(),
                            authCredentialRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();

            //set password to null to hide the values
            user.setPassword(null);

            //generate a token to use
            String passwordToken = jwtUtils.generateToken(user);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, passwordToken)
                    .body(user.getUsername());
        }catch (BadCredentialsException exception) {
            throw new Exception(("Invalid credentials"));
        }

    }

    //TODO: User Validate mapping with token
    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token, @AuthenticationPrincipal User user) {
        boolean checkValid;

        if(user != null) {
            checkValid = jwtUtils.validateToken(token, user);
        }else {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(checkValid);

    }

    //create a new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {
        Authentication authentication;

        try {
            // Encode the password for security
            String encodedPassword = customPasswordEncoder.getPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);

            // Persist the user in the database
            userRepo.save(user);

            // Authenticate with authorities
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            encodedPassword, // Use the encoded password for authentication
                            user.getAuthorities()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            User newUser = (User) authentication.getPrincipal();
            // Set password to null for security
            newUser.setPassword(null);

            // Generate token
            String passwordToken = jwtUtils.generateToken(newUser);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, passwordToken)
                    .body(newUser.getUsername());
        } catch (BadCredentialsException exception) {
            throw new Exception("Invalid credentials");
        }
    }

    //api call for authorities
    @GetMapping("/authorities")
    public ResponseEntity<List<String>> getAuthorities() {
        List<String> authorityName = authorityService.getAuthorityNames();
        return ResponseEntity.ok(authorityName);
    }
}
