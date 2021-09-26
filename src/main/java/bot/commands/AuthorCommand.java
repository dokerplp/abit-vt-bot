package bot.commands;

import bot.run.AbitVTBot;
import bot.util.CommandsUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;

public class AuthorCommand implements Command {

    public String getGREETING(String chatId) {
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", bot.getSql().selectLanguage(chatId).getLocale());
        return otherBundle.getString("author.greeting");
    }

    private final AbitVTBot bot;

    public AuthorCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId = CommandsUtil.getChatId(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(getGREETING(chatId));

        bot.execute(sendMessage);
    }

    @Override
    public String help(String chatId) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", bot.getSql().selectLanguage(chatId).getLocale());
        return helpBundle.getString("author");
    }

    @Override
    public String name() {
        return "/author";
    }
}
