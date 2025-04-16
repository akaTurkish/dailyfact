package com.turkish.dailyfact.telegram;

import com.turkish.dailyfact.model.Fact;
import com.turkish.dailyfact.service.FactService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotConfig config;
    private final FactService factService;

    public TelegramBot(TelegramBotConfig config, FactService factService) {
        super(config.getToken());
        this.config = config;
        this.factService = factService;
    }

    @Override
    public String getBotUsername() {
        return config.getUsername();
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Update received: " + update.getMessage().getText());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            if (text.equals("/start")) {
                send(chatId, "Привіт! 👋 Це бот 'Фактодня'. Надішли /fact, щоб дізнатися щось нове!");
            } else if (text.equals("/fact")) {
                Fact todayFact = factService.getDailyFact();
                String msg = "📘 *Факт дня:*\n\n" + todayFact.getFact() + "\n\n_" + todayFact.getDescription() + "_";
                send(chatId, msg);
            } else {
                send(chatId, "Спробуй команду /fact 😉");
            }
        }
    }

    private void send(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setParseMode("Markdown");
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}