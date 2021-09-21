package bot.commands;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SubjectsCommand implements Command{

    private final AbitVTBot bot;

    public SubjectsCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

    }

    @Override
    public String help() {
        return "/subjects - get list of subjects and their description";
    }

    @Override
    public String name() {
        return "/subjects";
    }
}
