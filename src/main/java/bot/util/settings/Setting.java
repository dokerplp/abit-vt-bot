package bot.util.settings;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Setting {
    void execute(Update update) throws TelegramApiException;
}
