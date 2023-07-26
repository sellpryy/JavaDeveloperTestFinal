package com.example.javadevelopertest.controller;

import com.example.javadevelopertest.dto.AuthenticationRequest;
import com.example.javadevelopertest.dto.AuthenticationResponse;
import com.example.javadevelopertest.security.JwtTokenUtil;
import com.example.javadevelopertest.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticate.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (BadCredentialsException e) {
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

