package bot.util;

import bot.enums.Stickers;
import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.UpdatesHandler;

public class BotUpdatesHandler implements UpdatesHandler {

    private final TextHandler textHandler;
    private final AbitVTBot bot;

    public BotUpdatesHandler(AbitVTBot bot) {
        this.bot = bot;
        this.textHandler = new TextHandler(bot);
    }

    public void newUpdate(Update update) throws TelegramApiException {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()){
                String text = message.getText().split(" ")[0];
                if (text.startsWith("/")) bot.invoke(text, update);
                else textHandler.analyze(message.getText(), update);
            }
            else if (message.hasVoice()){
                Stickers.VOICE.sendSticker(bot, update);
            } else if (message.hasSticker()) {
                System.out.println(message.getSticker().getFileId());
            }

        } else if(update.hasCallbackQuery()){

            String text = update.getCallbackQuery().getData();
            if (text.startsWith("/")) bot.invoke(text, update);

        }
    }
}
