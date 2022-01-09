package com.example.telebot.service;

import com.example.telebot.data.entity.User;
import com.example.telebot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public void saveOrUpdate(User user) {
        User userForSave;
        user.setUserLogin(user.getUserLogin() == null ? "LoginIsEmpty" : user.getUserLogin());
        Optional<User> userFromDB = repository.findById(user.getId());
        if (userFromDB.isPresent()) {
            userForSave = userFromDB.get();
            userForSave.setUserLogin(user.getUserLogin());
            userForSave.setUserFullName(user.getUserFullName());
            userForSave.setLastActivity(user.getLastActivity());
        } else {
            userForSave = user;
        }
        repository.save(userForSave);
    }
}
