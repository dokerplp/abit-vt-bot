package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.Translatable;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;

public class FaqCommand implements Command, Translatable {

    private String HELP;

    private final AbitVTBot bot;

    public FaqCommand(AbitVTBot bot) {
        this.bot = bot;
        bot.getTranslator().add(this);
        translate(Language.EN);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public String name() {
        return "/faq";
    }

    @Override
    public void translate(Language language) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", language.getLocale());

        HELP = helpBundle.getString("faq");
    }
}
