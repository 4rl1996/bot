package com.example.telebot.handler;

import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HandlerManager {

    private final LocationHandler locationHandler;

    public BotApiMethod handle(Update update) {
        if (update.hasMessage() && update.getMessage().getLocation() != null) {
            return buttonWrapper(locationHandler.getWeather(update));
        }
        if (update.hasMessage()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Могу подсказать, какая погода за окном");
            return buttonWrapper(message);
        }
        return null;
    }

    private SendMessage buttonWrapper(SendMessage message) {
        List<KeyboardButton> button = new ArrayList<>();
        button.add(KeyboardButton.builder().text("Поделиться геолокацией" + EmojiParser.parseToUnicode(":world_map:")).requestLocation(true).build());
        List<KeyboardRow> buttons = new ArrayList<>();
        buttons.add(new KeyboardRow(button));
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setKeyboard(buttons);
        message.setReplyMarkup(keyboard);
        return message;
    }
}
