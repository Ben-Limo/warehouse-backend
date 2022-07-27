package com.teckmils.warehousemanagementsystem.domain.user.service;

import com.teckmils.warehousemanagementsystem.domain.user.dto.UpdateUser;
import com.teckmils.warehousemanagementsystem.domain.user.dto.UserResponse;
import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import com.teckmils.warehousemanagementsystem.domain.user.repository.RoleRepository;
import com.teckmils.warehousemanagementsystem.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserResponse> getUsers() {
        final List<User> users = this.userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        users.forEach(user -> userResponses.add(
                new UserResponse(
                        user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getRole().getItemName(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                )
        ));

        return userResponses;
    }

    public UserResponse getUserById(final UUID id) {
        final User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole().getItemName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
    @Transactional
    public ResponseEntity<String> deleteUserByUid(final UUID id) {
        this.userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    public ResponseEntity<String> updateUserRole(final UpdateUser request) {
        final var email = request.email();
        return this.userRepository.findByEmail(email)
                .map(u -> this.updateRole(u, request.role()))
                .orElseThrow(() -> new UsernameNotFoundException("could not find email: " + email));

    }

    private ResponseEntity<String> updateRole(final User user, final String updateRole) {
        final var role = this.roleRepository.findByItemName(updateRole.toUpperCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setRole(role);
        this.userRepository.save(user);
        return new ResponseEntity<>("Role updated successfully!", HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("could not find email: " + email));

        return new com.teckmils.warehousemanagementsystem.domain.authentication.dto.UserDetails(user);
    }
}
