package com.example.telebot.handler;

import com.example.telebot.data.entity.LocationEdu;
import com.example.telebot.mapper.LocationDtoMapper;
import com.example.telebot.service.LocationService;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationHandler {

    private final LocationDtoMapper locationMapper;
    private final LocationService service;

    public SendMessage getWeather(Update update) {
        Location locationFromRequest = update.getMessage().getLocation();
        LocationEdu locationEdu = locationEdu(locationFromRequest);

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());

        List<String> weatherForecast = service.getWeather(locationMapper.entityToDto(locationEdu));
        StringBuilder text = text(weatherForecast);

        message.setText(text.toString());
        return message;
    }


    private LocationEdu locationEdu(Location locationFromRequest) {
        return LocationEdu.builder()
                .lon(locationFromRequest.getLongitude())
                .lat(locationFromRequest.getLatitude())
                .build();
    }

    private StringBuilder text(List<String> weatherForecast) {
        return new StringBuilder()
                .append(EmojiParser.parseToUnicode(":timer_clock:"))
                .append(": ")
                .append(weatherForecast.get(0))
                .append("\n")
                .append(EmojiParser.parseToUnicode(":sunrise:"))
                .append(": ")
                .append(weatherForecast.get(1))
                .append("\n")
                .append(EmojiParser.parseToUnicode(":new_moon_with_face:"))
                .append(": ")
                .append(weatherForecast.get(2))
                .append("\n")
                .append(EmojiParser.parseToUnicode(":thermometer:"))
                .append(": ")
                .append(weatherForecast.get(3))
                .append("\n")
                .append(EmojiParser.parseToUnicode(":cloud_tornado:"))
                .append(": ")
                .append(weatherForecast.get(4));
    }
}
