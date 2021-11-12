package bots;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Eugen Landarenko
 */
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();

        try {
            telegram.registerBot(new JkayBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}