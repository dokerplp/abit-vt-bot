package bot.commands;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;

public class FaqCommand implements Command{

    private final AbitVTBot bot;

    public FaqCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

    }

    @Override
    public String help(String chatId) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", bot.getSql().selectLanguage(chatId).getLocale());
        return helpBundle.getString("faq");
    }

    @Override
    public String name() {
        return "/faq";
    }

}
