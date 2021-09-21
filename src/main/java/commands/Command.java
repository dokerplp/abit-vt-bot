package commands;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import util.Log;

abstract public class Command extends BotCommand {

    private final Log log = new Log();

    protected Command(@NonNull String command, @NonNull String description) {
        super(command, description);
    }

    public static void main(String[] args) {
        KeyBoard
    }

}
