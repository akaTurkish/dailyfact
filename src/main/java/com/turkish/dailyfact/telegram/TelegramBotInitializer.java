package com.turkish.dailyfact.telegram;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotInitializer {

    private final TelegramBot bot;

    public TelegramBotInitializer(TelegramBot bot) {
        this.bot = bot;
    }

    @PostConstruct
    public void start() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
            System.out.println("✅ Telegram bot registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}