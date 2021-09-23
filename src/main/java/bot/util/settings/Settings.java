package bot.util.settings;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Settings implements Setting {
    private final AbitVTBot bot;

    public Settings(AbitVTBot bot) {
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
        sendMessage.setText("List of available languages:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        keyboardMarkup.setResizeKeyboard(true);
        row.add("⚙️ Ru \uD83C\uDDF7\uD83C\uDDFA");
        row.add("⚙️ En \uD83C\uDDFA\uD83C\uDDF8");

        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        bot.execute(sendMessage);
    }
}
