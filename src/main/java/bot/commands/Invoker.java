package bot.commands;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private final AbitVTBot bot;

    public Invoker(AbitVTBot bot) {
        this.bot = bot;
    }

    private final Map<String, Command> commandMap = new HashMap<>();

    public void add(String key, Command command){
        commandMap.put(key, command);
    }

    public void execute(String key, Update update) throws TelegramApiException {
        Command command = commandMap.get(key);
        if (command != null) command.execute(update);
        else {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Sorry this command doesn't exist");
            bot.execute(message);
        }
    }
}
