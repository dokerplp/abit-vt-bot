package bot.util.settings;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.CommandsUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EN implements Setting{
    private final AbitVTBot bot;

    public EN(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        String chatId = CommandsUtil.getChatId(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Language was changed to english");

        bot.getSql().updateLanguage(chatId, Language.EN);

        bot.execute(sendMessage);
    }
}
