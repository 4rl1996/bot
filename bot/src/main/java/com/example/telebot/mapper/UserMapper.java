package com.example.telebot.mapper;

import com.example.telebot.data.entity.User;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UserMapper {

    public User getUserInfo(Update update) {
        return User.builder()
                .id(update.getMessage().getChatId())
                .userLogin(update.getMessage().getFrom().getUserName())
                .userFullName(update.getMessage().getFrom().getFirstName() + " "
                        + update.getMessage().getFrom().getLastName())
                .date(update.getMessage().getDate())
                .build();
    }
}
