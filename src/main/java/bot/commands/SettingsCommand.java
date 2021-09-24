package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.Translatable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsCommand implements Command, Translatable {

    private String HELP;
    private String TEXT;
    private String SETTINGS;

    private final AbitVTBot bot;

    public SettingsCommand(AbitVTBot bot) {
        this.bot = bot;
        bot.getTranslator().add(this);
        translate(Language.EN);
    }


    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId;
        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendMessage.setChatId(chatId);
        sendMessage.setText(TEXT);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        keyboardMarkup.setResizeKeyboard(true);
        row.add(SETTINGS);

        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        bot.execute(sendMessage);
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public String name() {
        return "/settings";
    }

    @Override
    public void translate(Language language) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", language.getLocale());
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", language.getLocale());

        HELP = helpBundle.getString("settings");
        TEXT = otherBundle.getString("settings.text");
        SETTINGS = otherBundle.getString("settings.settings");
    }
}
