package com.turkish.dailyfact.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_CHATS")
@Data
@NoArgsConstructor
public class UserChat {

    public UserChat(Long chatId) {
        this.chatId = chatId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

}