package bot.util;

import bot.enums.Stickers;
import bot.run.AbitVTBot;
import bot.util.settings.SettingsInvoker;
import bot.util.settings.SettingsReceiver;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
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
        else text(text, update);
    }

    public void text(String text, Update update) throws TelegramApiException {

        String chatId = CommandsUtil.getChatId(update);

        SendSticker sticker = new SendSticker();
        sticker.setChatId(chatId);
        sticker.setSticker(new InputFile().setMedia(Stickers.KOTIKI.toString()));

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("I don't know answer on question:\n\"" + text + "\"");

        bot.execute(message);
        bot.execute(sticker);
    }
}
