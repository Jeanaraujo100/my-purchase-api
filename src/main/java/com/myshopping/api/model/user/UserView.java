package com.myshopping.api.model.user;

public record UserView(
        String id,
        String name,
        String phone,
        String password
) {
    public UserView(Users user) {
        this(user.getId(), user.getName(), user.getPhone().toString(), user.getPassword());
    }
}
