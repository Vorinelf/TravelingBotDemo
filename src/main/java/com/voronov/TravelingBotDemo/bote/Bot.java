package com.voronov.TravelingBotDemo.bote;

import com.voronov.TravelingBotDemo.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@PropertySource("classpath:telegram.properties")
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private BotService botService;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String cityName = message.getText();
            List<String> descriptions = botService.findCityByName(cityName);
            String totalDescriptions = descriptions.stream()
                    .distinct().collect(Collectors.joining(";" + "\n"));
            botService.findCityByName(message.getText());
            sendMsg(message, totalDescriptions);

        }

    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
