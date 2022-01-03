package com.example.telebot.mapper;

import com.example.telebot.data.dto.LocationDTO;
import com.example.telebot.data.entity.LocationEdu;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LocationDtoMapper {

    public LocationDTO entityToDto(LocationEdu location) {

        return new LocationDTO(location.getLat(),
                location.getLon(),
                Arrays.asList("hourly", "minutely", "daily", "alerts"),
                "993f740fb37f4a0c5478a56034b089d2",
                "metric",
                "ru");
    }
}
