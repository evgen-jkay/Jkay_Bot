package bots.updateshandlers;

import bots.BotConfig;
import bots.commands.HelpCommand;
import bots.commands.StartCommand;
import bots.commands.StopCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

/**
 * This handler mainly works with commands to demonstrate the Commands feature of the API
 *
 * @author Timo Schulz (Mit0x2)
 */
public class CommandsHandler extends TelegramLongPollingCommandBot {

    public static final String LOGTAG = "COMMANDSHANDLER";

    /**
     * Constructor.
     */
    public CommandsHandler(String botUsername) {
        super(botUsername);

        register(new StartCommand());
        register(new StopCommand());
        HelpCommand helpCommand = new HelpCommand(this);
        register(helpCommand);

        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("The command '" + message.getText() + "' is not known by this bot. Here comes some help ");
            try {
                absSender.execute(commandUnknownMessage);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
        });
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                SendMessage echoMessage = new SendMessage();
                echoMessage.setChatId(message.getChatId());
                echoMessage.setText("Hey heres your message:\n" + message.getText());

                try {
                    execute(echoMessage);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}