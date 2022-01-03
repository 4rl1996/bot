package com.example.telebot.data.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationEdu {

    private Double lat;

    private Double lon;
}
