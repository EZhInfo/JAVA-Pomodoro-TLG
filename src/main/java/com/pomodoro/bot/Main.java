package com.pomodoro.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.text.html.parser.TagElement;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new EchoBot());
    }

    static class EchoBot extends TelegramLongPollingBot {

        @Override
        public String getBotUsername() {
            return "Попугай-бот";
        }

        @Override
        public String getBotToken() {
            return "5748459487:AAEZPSGaEQqCwVuWfIInby1W28YMrvlm13k";
        }

        private static int userCount = 0;
        @Override
        public void onUpdateReceived(Update update) {
            int userCount = 0;
            if (update.hasMessage() && update.getMessage().hasText()) {
                var chatId = update.getMessage().getChatId().toString();

                if (update.getMessage().getText().equals("/start")) {
                    userCount++;
                    sendMsg(chatId, "Привет, я попугай-бот. Буду повторять все  за тобой.");
                } else {
                    sendMsg(chatId, update.getMessage().getText());
                }
            }
            System.out.println("Количество пользователей " + userCount);
        }

        private void sendMsg(String chatId, String text) {
            SendMessage msg = new SendMessage(chatId, text);
            try {
                execute(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
