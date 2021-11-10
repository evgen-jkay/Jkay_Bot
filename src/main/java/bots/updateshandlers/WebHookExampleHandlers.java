package bots.updateshandlers;

import bots.BotConfig;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author pithera
 * @version 1.0
 * Simple Webhook example
 */
public class WebHookExampleHandlers extends TelegramWebhookBot {
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Well, all information looks like noise until you break the code.");
            return sendMessage;
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public String getBotPath() {
        return BotConfig.BOT_NAME; //arbitrary path to deliver updates on, username is an example.
    }
}