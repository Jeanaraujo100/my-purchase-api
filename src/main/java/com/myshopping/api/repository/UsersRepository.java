package com.myshopping.api.repository;

import com.myshopping.api.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    UserDetails findByPhone(Long phone);

    UserDetails findByName(String name);

    boolean existsByPhone(Long phone);
}
