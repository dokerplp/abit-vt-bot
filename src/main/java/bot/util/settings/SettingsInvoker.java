package bot.util.settings;

import bot.util.TextHandler;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedHashMap;
import java.util.Map;

public class SettingsInvoker {
    private final TextHandler textHandler;
    private final Map<String, Setting> settingMap = new LinkedHashMap<>();

    public SettingsInvoker(TextHandler textHandler) {
        this.textHandler = textHandler;
    }

    public void add(String key, Setting setting) {
        settingMap.put(key, setting);
    }

    public void execute(String key, Update update) throws TelegramApiException {
        Setting setting = settingMap.get(key);
        if (setting != null) setting.execute(update);
        else {
            textHandler.text(key);
        }

    }
}
