package com.example.telebot.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

    private Double lat;

    private Double lon;

    private List<String> exclude;

    private String appid;

    private String units;

    private String lang;

}
