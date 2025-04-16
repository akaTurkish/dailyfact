package com.turkish.dailyfact.telegram;

import com.turkish.dailyfact.model.Fact;
import com.turkish.dailyfact.model.UserChat;
import com.turkish.dailyfact.repository.UserChatRepository;
import com.turkish.dailyfact.service.FactService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

@Component
public class FactScheduler {

    private final FactService factService;
    private final UserChatRepository chatRepository;
    private final AbsSender bot;

    public FactScheduler(FactService factService, UserChatRepository chatRepository, AbsSender bot) {
        this.factService = factService;
        this.chatRepository = chatRepository;
        this.bot = bot;
    }

    // Щодня о 10:00 за серверним часом
    @Scheduled(cron = "0 30 21 * * *")
    public void sendFactToAllUsers() {
        Fact fact = factService.getDailyFact();
        String message = "📘 *Факт дня:*\n\n" + fact.getFact() + "\n\n_" + fact.getDescription() + "_\n\n🔎 Джерело: " + fact.getSource();

        List<UserChat> users = chatRepository.findAll();
        for (UserChat user : users) {
            try {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(user.getChatId().toString());
                sendMessage.setText(message);
                sendMessage.setParseMode("Markdown");
                bot.execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}