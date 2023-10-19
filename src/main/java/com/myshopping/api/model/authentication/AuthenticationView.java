package com.myshopping.api.model.authentication;

public record AuthenticationView (
        String token,
        String userId
){
}
