package com.example.telebot.data.entity;

import com.example.telebot.utils.EduBotUtils;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity(name = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    Long id;

    @Nullable
    String userLogin;

    @NotNull
    String userFullName;

    @NotNull
    ZonedDateTime lastActivity;

    @Builder
    public static User createUser(Long id, String userLogin, String userFullName, Integer date) {
        ZonedDateTime formattedTime = EduBotUtils.timeConverter(date);
        return new User(id, userLogin, userFullName, formattedTime);
    }
}
