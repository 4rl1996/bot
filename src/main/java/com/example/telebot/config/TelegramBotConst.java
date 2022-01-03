package com.example.telebot.config;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TelegramBotConst {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.url}")
    private String url;
}
