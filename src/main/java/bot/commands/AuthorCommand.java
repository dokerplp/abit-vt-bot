package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.Translatable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;

public class AuthorCommand implements Command, Translatable {

    private String GREETING;
    private String HELP;

    private final AbitVTBot bot;

    public AuthorCommand(AbitVTBot bot) {
        this.bot = bot;
        bot.getTranslator().add(this);
        translate(Language.EN);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId;

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(toString());

        bot.execute(sendMessage);
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public String name() {
        return "/author";
    }

    @Override
    public String toString() {
        return GREETING;
    }

    @Override
    public void translate(Language language) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", language.getLocale());
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", language.getLocale());

        HELP = helpBundle.getString("author");
        GREETING = otherBundle.getString("author.greeting");
    }
}
