package bot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PlanCommand implements Command{
    @Override
    public void execute(Update update) throws TelegramApiException {

    }

    @Override
    public String help() {
        return "/plan - get syllabus of vt";
    }

    @Override
    public String name() {
        return "/plan";
    }
}
