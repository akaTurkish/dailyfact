package com.turkish.dailyfact.repository;

import com.turkish.dailyfact.model.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    boolean existsByChatId(Long chatId);
}