package com.example.telebot;

import com.example.telebot.config.TelegramBotConst;
import com.example.telebot.data.entity.User;
import com.example.telebot.handler.HandlerManager;
import com.example.telebot.mapper.UserMapper;
import com.example.telebot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@RequiredArgsConstructor
public class EduTestBot extends TelegramLongPollingBot {

    private final UserService service;
    private final TelegramBotConst config;
    private final HandlerManager manager;
    private final UserMapper mapper;

    @Override
    public String getBotUsername() {
        return config.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(manager.handle(update));
            service.saveOrUpdate(mapper.getUserInfo(update));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onUpdateReceived(Update update) {
//        // We check if the update has a message and the message has text
//        if (update.hasMessage() && (update.getMessage().hasText() || update.getMessage().getLocation() != null)) {
//            String text = update.getMessage().getText();
//            String userLogin = update.getMessage().getFrom().getUserName();
//            String userFullName = update.getMessage().getFrom().getFirstName() + " "
//                    + update.getMessage().getFrom().getLastName();
//            Long userId = update.getMessage().getChatId();
//            Integer date = update.getMessage().getDate();
//            List<KeyboardButton> button = new ArrayList<>();
//            button.add(KeyboardButton.builder().text("Привет вам от Ромки!" + EmojiParser.parseToUnicode(":boy:")).build());
//            button.add(KeyboardButton.builder().text("Привет вам от Шрека!" + EmojiParser.parseToUnicode(":green_heart:")).build());
//
//            List<KeyboardButton> button1 = new ArrayList<>();
//            button1.add(KeyboardButton.builder().text("Привет вам!").requestLocation(true).build());
//
//
//
//            List<KeyboardRow> buttons = new ArrayList<>();
//            buttons.add(new KeyboardRow(button));
//            buttons.add(new KeyboardRow(button1));
//
//            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText("Проваливайте с моего болота!");
//            File file = new File("/home/user/Projects/Java/TeleBot/src/main/resources/196-1969693_shrek-pixel-art-maker.png");
//            ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
//            keyboard.setKeyboard(buttons);
//            message.setReplyMarkup(keyboard);
//
//            InputFile inputFile = new InputFile(file);
//            SendPhoto photo = new SendPhoto();
//            photo.setPhoto(inputFile);
//            photo.setChatId(update.getMessage().getChatId().toString());
//            ObjectMapper o = new ObjectMapper();
//
//            try {
//                execute(message);// Call method to send the message
//                execute(photo);
//                    service.saveOrUpdate(User.builder()
//                            .id(userId)
//                            .userLogin(userLogin)
//                            .userFullName(userFullName)
//                            .date(date)
//                            .build());
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }

//    }
}
