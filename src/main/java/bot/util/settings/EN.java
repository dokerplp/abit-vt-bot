package bot.util.settings;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.Translatable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;

public class EN implements Setting{
    private final AbitVTBot bot;

    public EN(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        String chatId;
        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Language was changed to english");

        bot.setLanguage(Language.EN);
        bot.getTranslator().translate(Language.EN);

        bot.execute(sendMessage);
    }
}
