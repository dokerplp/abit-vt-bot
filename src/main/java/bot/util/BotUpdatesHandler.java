package bot.util;

import bot.enums.Stickers;
import bot.run.AbitVTBot;
import bot.test.StickerTest;
import org.telegram.telegrambots.meta.api.interfaces.Validable;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.UpdatesHandler;

public class BotUpdatesHandler implements UpdatesHandler {

    private final AbitVTBot bot;

    public BotUpdatesHandler(AbitVTBot bot) {
        this.bot = bot;
    }

    public void newUpdate(Update update) throws TelegramApiException {

        //StickerTest.getStickerID(update);

        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()){
                String text = message.getText().split(" ")[0];
                if (text.startsWith("/")) bot.invoke(text, update);
            }
            else if (message.hasVoice()){
                Stickers.VOICE.sendSticker(bot, update);
            }
            else {
                SendDice dice = new SendDice();
                dice.setChatId(message.getChatId().toString());
                bot.execute(dice);
            }

        } else if(update.hasCallbackQuery()){

            String text = update.getCallbackQuery().getData();
            if (text.startsWith("/")) bot.invoke(text, update);

//            try {
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setText(update.getCallbackQuery().getData());
//                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
//                bot.execute(sendMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
    }
}
