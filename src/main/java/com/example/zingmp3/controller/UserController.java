package com.example.zingmp3.controller;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.model.User;
import com.example.zingmp3.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<User> getCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody User editUser) {

        User currentUser = userService.getCurrentUser();
        
        if(editUser.getAvatar().isEmpty()){
            editUser.setAvatar(currentUser.getAvatar());
        }
        currentUser.setAddress(editUser.getAddress());
        currentUser.setEmail(editUser.getEmail());
        currentUser.setPhone(editUser.getPhone());
        currentUser.setAvatar(editUser.getAvatar());
        userService.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
