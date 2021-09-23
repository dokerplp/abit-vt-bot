package bot.commands;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SettingsCommand implements Command {
    private final AbitVTBot bot;

    public SettingsCommand(AbitVTBot bot) {
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
        sendMessage.setText("Here it is:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        keyboardMarkup.setResizeKeyboard(true);
        row.add("⚙️ Settings \uD83D\uDEE0");

        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        bot.execute(sendMessage);
    }

    @Override
    public String help() {
        return "/settings - change bot settings";
    }

    @Override
    public String name() {
        return "/settings";
    }
}
