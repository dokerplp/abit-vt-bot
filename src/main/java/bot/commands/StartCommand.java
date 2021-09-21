package bot.commands;

import bot.enums.Stickers;
import bot.run.AbitVTBot;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;

public class StartCommand implements Command{

    private final AbitVTBot bot;

    public StartCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello");


        Sticker sticker = new Sticker();
        sticker.setFileId(Stickers.WELCOME_TO_THE_CLUB.toString());

        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(message.getChatId().toString());
        sendSticker.setSticker(new InputFile().setMedia(Stickers.WELCOME_TO_THE_CLUB.toString()));


        bot.execute(sendMessage);
        bot.execute(sendSticker);
    }
}
