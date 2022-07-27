package com.teckmils.warehousemanagementsystem.domain.authentication.service;

import com.teckmils.warehousemanagementsystem.domain.authentication.dto.RegisterRequest;
import com.teckmils.warehousemanagementsystem.domain.user.UserRole;
import com.teckmils.warehousemanagementsystem.domain.user.model.Role;
import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import com.teckmils.warehousemanagementsystem.domain.user.repository.RoleRepository;
import com.teckmils.warehousemanagementsystem.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Value("${spring.mail.username")
    private String senderMail;

    public AuthenticationService(final AuthenticationManager authenticationManager,
                           final UserRepository userRepository,
                           final RoleRepository roleRepository,
                           final PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> authenticateUser(final String email, final String password) {
        final Authentication authToken = new UsernamePasswordAuthenticationToken(email, password);

        try {
            final Authentication authentication = this.authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User authenticated successfully!", HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>("User could not be authenticated!", HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<String> registerUser(final RegisterRequest registerRequest) {
        if (this.userRepository.existsByEmail(registerRequest.email())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        final var user = new User();

        user.setUserName(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setPassword(this.passwordEncoder.encode(registerRequest.password()));

        final Optional<Role> role = this.roleRepository.findByItemName(UserRole.READ_ONLY);

        if (role.isEmpty()) {
            return new ResponseEntity<>("No suitable roles found for user! Check your database for roles!", HttpStatus.BAD_REQUEST);
        }

        user.setRole(role.get());

        this.userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}

