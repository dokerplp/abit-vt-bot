package bot.commands;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class FaqCommand implements Command{

    private final AbitVTBot bot;

    public FaqCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

    }

    @Override
    public String help() {
        return "/faq - common questions";
    }

    @Override
    public String name() {
        return "/faq";
    }
}
