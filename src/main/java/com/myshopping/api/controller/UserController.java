package com.myshopping.api.controller;

import com.myshopping.api.model.user.UserForm;
import com.myshopping.api.model.user.UserUpdate;
import com.myshopping.api.model.user.UserView;
import com.myshopping.api.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    //    @GetMapping("/{phone}")
//    public ResponseEntity authenticationUser(@PathVariable("phone") Long phone){
//        var user = userService.findUser(phone);
//        return ResponseEntity.ok(user);
//    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity addUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder){
        var user = userService.addUser(userForm);
        var uri = uriBuilder.path("user/detail/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid UserUpdate userUpdate){
        UserView userView = userService.update(userUpdate);
        return ResponseEntity.ok(userView);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity getAll(@PathVariable("id") String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detailUser(@PathVariable("id") String id){
        UserView userView = userService.detailUser(id);
        return ResponseEntity.ok(userView);
    }

    @GetMapping
    public ResponseEntity getAll(){
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
