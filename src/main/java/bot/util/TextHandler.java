package bot.util;

import bot.run.AbitVTBot;
import bot.util.settings.SettingsInvoker;
import bot.util.settings.SettingsReceiver;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TextHandler {
    private final AbitVTBot bot;

    private final SettingsInvoker invoker;
    private final SettingsReceiver receiver;

    public TextHandler(AbitVTBot bot) {
        this.bot = bot;
        this.invoker = new SettingsInvoker(this);
        this.receiver = new SettingsReceiver(bot);
        receiver.setSettings(invoker);
    }

    public void analyze(String text, Update update) throws TelegramApiException {
        if (text.startsWith("⚙️")) {
            invoker.execute(text, update);
        }
    }

    public void text(String text) {

    }
}
