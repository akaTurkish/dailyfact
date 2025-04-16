package com.turkish.dailyfact.telegram;

import com.turkish.dailyfact.model.Fact;
import com.turkish.dailyfact.service.FactService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

            switch (text) {
                case "/start" -> {
                    String name = update.getMessage().getFrom().getUserName();
                    String msg = "Привіт, " + name + "! \nЦе бот з цікавим фактом на кожен день.\n\nДоступні команди:\n/daily_fact - отримати факт дня\n/random - отримати випадковий факт";
                    SendMessage message = new SendMessage();
                    message.setChatId(chatId);
                    message.setText(msg);
                    message.enableMarkdown(false); // Вимкнути Markdown
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/daily_fact" -> {
                    Fact todayFact = factService.getDailyFact();
                    String msg = "📘 *Факт дня:*\n\n" + todayFact.getFact() + "\n\n_" + todayFact.getDescription() + "_\n\n_" + todayFact.getSource() + "_";
                    send(chatId, msg);
                }
                case "/random" -> {
                    Fact randomFact = factService.getRandomFact();
                    String msg = "📘 *Випадковий факт:*\n\n" + randomFact.getFact() + "\n\n_" + randomFact.getDescription() + "_\n\n_" + randomFact.getSource() + "_";
                    send(chatId, msg);
                }
                default -> send(chatId, "Список команд:\n " +
                        "/daily_fact \n" +
                        "/random");
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