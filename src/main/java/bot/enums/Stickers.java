package bot.enums;

import bot.run.AbitVTBot;
import bot.util.CommandsUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public enum Stickers implements Serializable {

    WELCOME_TO_THE_CLUB("CAACAgIAAxkBAAOdYUo0m_syBRShgkKxCTjJqPWLC14AAh8AA_HzcxPzDm9d0wYLCiEE"),
    VOICE("CAACAgIAAxkBAAIBMWFKc0fe3iVLjOG5iBrXuYb1e6EjAAIXAAOr26Y545BSbBihquIhBA"),
    KOTIKI("CAACAgIAAxkBAAICp2FQwwXFjFrrzyHbDBrTQprgQWiQAAIrAAP2zkAb8nPTZBjynH8hBA");

    private final String id;

    Stickers(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return id;
    }

    public void sendSticker(AbitVTBot bot, Update update) throws TelegramApiException {

        String chatId = CommandsUtil.getChatId(update);

        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(chatId);
        sendSticker.setSticker(new InputFile().setMedia(toString()));

        bot.execute(sendSticker);
    }
}
