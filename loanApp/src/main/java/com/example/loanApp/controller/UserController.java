package com.example.loanApp.controller;

import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.model.UserDto;
import com.example.loanApp.repository.UserRepository;
import com.example.loanApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<UserInfo> createUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/view/loans")
    @ResponseBody
    public SuccessResponse viewLoan() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.viewLoan(username);
    }
}
