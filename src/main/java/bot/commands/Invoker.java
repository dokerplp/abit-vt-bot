package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Invoker{

    private String TEXT;

    private final AbitVTBot bot;
    private final Map<String, Command> commandMap = new LinkedHashMap<>();

    public Invoker(AbitVTBot bot) {
        this.bot = bot;
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public void add(String key, Command command) {
        commandMap.put(key, command);
    }

    public void execute(String key, Update update) throws TelegramApiException {
        Command command = commandMap.get(key);
        if (command != null) command.execute(update);
        else {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(TEXT);
            bot.execute(message);
        }
    }
}
