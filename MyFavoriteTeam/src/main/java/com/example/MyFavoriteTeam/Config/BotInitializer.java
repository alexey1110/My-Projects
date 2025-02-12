package com.example.MyFavoriteTeam.Config;

import com.example.MyFavoriteTeam.Service.MatchBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    private final MatchBot matchBot;

    @Autowired
    public BotInitializer(MatchBot matchBot) {
        this.matchBot = matchBot;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(matchBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
