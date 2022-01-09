package com.example.telebot.service;

import com.example.telebot.config.TelegramBotConst;
import com.example.telebot.data.dto.LocationDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final RestTemplate restTemplate;
    private final TelegramBotConst botConst;


    public List<String> getWeather(LocationDTO dto) {
        Map<String, String> urlParams = getMap(dto);
        String weatherServerResponse = restTemplate.getForObject(botConst.getUrl(), String.class, urlParams);
        JSONObject obj = new JSONObject(weatherServerResponse);
        String date =  unixToTime(obj.getJSONObject("current").getInt("dt"), obj.getString("timezone"));
        String sunrise = unixToTime(obj.getJSONObject("current").getInt("sunrise"), obj.getString("timezone"));
        String sunset = unixToTime(obj.getJSONObject("current").getInt("sunset"), obj.getString("timezone"));
        String temp = obj.getJSONObject("current").getFloat("temp") + " C";
        String wind = obj.getJSONObject("current").getFloat("wind_speed") + " м/с";

        List<String> params = new ArrayList<>();
        params.add(date);
        params.add(sunrise);
        params.add(sunset);
        params.add(temp);
        params.add(wind);
        return params;
    }


    private Map<String, String> getMap(LocationDTO dto) {
        Map<String, String> map = new HashMap<>();
        map.put("lat", dto.getLat().toString());
        map.put("lon", dto.getLon().toString());
        map.put("exclude", dto.getExclude().stream()
                .map(it -> toString())
                .collect(Collectors.joining(",")));
        map.put("appid", dto.getAppid());
        map.put("lang", dto.getLang());
        map.put("units", dto.getUnits());
        return map;
    }

    private String unixToTime(Integer time, String zone) {
        ZonedDateTime messageTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.of(zone));
        return messageTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
