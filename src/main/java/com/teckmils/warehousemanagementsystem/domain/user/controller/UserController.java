package com.teckmils.warehousemanagementsystem.domain.user.controller;

import com.teckmils.warehousemanagementsystem.domain.user.dto.UpdateUser;
import com.teckmils.warehousemanagementsystem.domain.user.dto.UserResponse;
import com.teckmils.warehousemanagementsystem.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<UserResponse> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/api/users/{id}")
    public UserResponse getUser(@PathVariable final UUID id) {
        return this.userService.getUserById(id);
    }

    @PatchMapping("/api/users/update-role")
    public ResponseEntity<String> updateRole(@RequestBody final UpdateUser request) {
        return this.userService.updateUserRole(request);
    }

    @DeleteMapping("/api/users/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable @NotNull final UUID uid) {
        return this.userService.deleteUserByUid(uid);
    }
}
