package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.entities.User;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class Usercontroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

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
    //TODO: User logout mapping
    //TODO: User register mapping

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

}
