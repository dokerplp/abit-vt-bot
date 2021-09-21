package bot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class LinksCommand implements Command{
    @Override
    public void execute(Update update) throws TelegramApiException {

    }

    @Override
    public String help() {
        return "/links - get useful links";
    }

    @Override
    public String name() {
        return "/links";
    }
}
