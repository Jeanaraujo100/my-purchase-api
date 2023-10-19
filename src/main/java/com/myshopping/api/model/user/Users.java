package com.myshopping.api.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users implements UserDetails {
    @Id
    private String id;
    private String name;
    private Long phone;
    private String password;

    public Users(UserForm userForm) {
        this(
                UUID.randomUUID().toString(),
                userForm.name(),
                Long.parseLong(userForm.phone()),
                userForm.password()
        );
    }

    public void update(UserUpdate userUpdate) {
        if (userUpdate.name() != null){
            this.name = userUpdate.name();
        }
        if (userUpdate.phone() != null){
            this.phone = Long.parseLong(userUpdate.phone());
        }
        if (userUpdate.password() != null){
            this.password = userUpdate.password();
        }
    }

    public void hastPassword(){
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return phone.toString();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
