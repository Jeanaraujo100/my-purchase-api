package com.myshopping.api.controller;

import com.myshopping.api.model.authentication.AuthenticationView;
import com.myshopping.api.model.user.AuthenticationData;
import com.myshopping.api.model.user.Users;
import com.myshopping.api.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData authenticationData){
        var tokenAuthentication = new UsernamePasswordAuthenticationToken(authenticationData.phone(), authenticationData.password());
        System.out.println(authenticationData);
        var authentication = manager.authenticate(tokenAuthentication);
        var tokenJWT = tokenService.generateToken((Users) authentication.getPrincipal());
        var authenticationView = new AuthenticationView(tokenJWT, ((Users) authentication.getPrincipal()).getId());
        return ResponseEntity.ok(authenticationView);
    }

}
