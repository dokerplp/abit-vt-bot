package bot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SubjectsCommand implements Command{
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
