package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.CommandsUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Invoker{

    private String getTEXT(String chatId){
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", bot.getSql().selectLanguage(chatId).getLocale());
        return otherBundle.getString("invoker.text");
    }

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
            String chatId = CommandsUtil.getChatId(update);
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(getTEXT(chatId));
            bot.execute(message);
        }
    }
}
