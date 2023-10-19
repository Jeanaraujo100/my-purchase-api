package com.myshopping.api.service;

import com.myshopping.api.model.user.UserForm;
import com.myshopping.api.model.user.UserUpdate;
import com.myshopping.api.model.user.UserView;
import com.myshopping.api.model.user.Users;
import com.myshopping.api.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public UserView addUser(UserForm userForm) {
        Users user = new Users(userForm);
        user.hastPassword();
        usersRepository.save(user);
        return new UserView(user);
    }

//    public UserView findUser(Long phone) {
//        if (usersRepository.existsByPhone(phone)){
//            var user = usersRepository.findByPhone(phone);
//            return new UserView(user);
//        }
//        throw new EntityNotFoundException();
//    }

    public List<UserView> findAll() {
        var users = usersRepository.findAll();
        return users.stream().map(UserView::new).toList();
    }

    public UserView update(UserUpdate userUpdate) {
        var user = usersRepository.getReferenceById(userUpdate.id());
        user.update(userUpdate);
        return new UserView(user);
    }

    public void delete(String id) {
        usersRepository.deleteById(id);
    }

    public UserView detailUser(String id) {
        var user = usersRepository.getReferenceById(id);
        return new UserView(user);
    }
}
