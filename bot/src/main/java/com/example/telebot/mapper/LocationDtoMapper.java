package com.example.telebot.mapper;

import com.example.telebot.data.dto.LocationDTO;
import com.example.telebot.data.entity.LocationEdu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LocationDtoMapper {

    @Value("${bot.appid}")
    private String appid;

    public LocationDTO entityToDto(LocationEdu location) {

        return new LocationDTO(location.getLat(),
                location.getLon(),
                Arrays.asList("hourly", "minutely", "daily", "alerts"),
                appid,
                "metric",
                "ru");
    }
}
