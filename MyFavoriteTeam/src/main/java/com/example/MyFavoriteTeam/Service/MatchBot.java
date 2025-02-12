package com.example.MyFavoriteTeam.Service;

import com.example.MyFavoriteTeam.Config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class MatchBot extends TelegramLongPollingBot {

    private final BotConfig config;
    @Autowired
    private SubscriprionService subscriptionService;

    @Autowired
    public MatchBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            // Обработка основных команд
            switch (messageText) {
                case "/start":
                    sendMessage(chatId, "Добро пожаловать в MatchBot!");
                    return;
                default:
                    break;
            }

            // Обработка подписок
            if (messageText.startsWith("/subscribe")) {
                String teamName = messageText.split(" ")[1];
                if (subscriptionService.subscribe(chatId, teamName)) {
                    sendMessage(chatId, "Вы успешно подписались на матчи команды " + teamName);
                } else {
                    sendMessage(chatId, "Команда не найдена или вы уже подписаны.");
                }
            } else if (messageText.startsWith("/unsubscribe")) {
                String teamName = messageText.split(" ")[1];
                if (subscriptionService.unsubscribe(chatId, teamName)) {
                    sendMessage(chatId, "Вы успешно отписались от матчей команды " + teamName);
                } else {
                    sendMessage(chatId, "Команда не найдена или вы не были подписаны.");
                }
            } else if (messageText.equals("/list")) {
                List<String> subscriptions = subscriptionService.getUserSubscriptions(chatId);
                if (subscriptions.isEmpty()) {
                    sendMessage(chatId, "У вас нет подписок.");
                } else {
                    sendMessage(chatId, "Ваши подписки: " + String.join(", ", subscriptions));
                }
            } else {
                sendMessage(chatId, "Неизвестная команда.");
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
