package bot.util.settings;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RU implements Setting {
    private final AbitVTBot bot;

    public RU(AbitVTBot bot) {
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
        sendMessage.setText("Язык был сменен на русский");

        bot.execute(sendMessage);
    }
}
