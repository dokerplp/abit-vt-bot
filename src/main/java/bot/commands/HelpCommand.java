package bot.commands;

import bot.enums.Language;
import bot.run.AbitVTBot;
import bot.util.Translatable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelpCommand implements Command, Translatable {

    private String HELP;
    private String TEXT;

    private final AbitVTBot bot;

    public HelpCommand(AbitVTBot bot) {
        this.bot = bot;
        bot.getTranslator().add(this);
        translate(Language.EN);
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId;

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendMessage.setChatId(chatId);
        sendMessage.setText(TEXT);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        for (Command command : bot.availableCommands().values()){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(command.help());
            button.setCallbackData(command.name());
            List<InlineKeyboardButton> bl = new ArrayList<>();
            bl.add(button);
            buttons.add(bl);
        }

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(buttons);

        sendMessage.setReplyMarkup(keyboard);

        bot.execute(sendMessage);
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public String name() {
        return "/help";
    }

    @Override
    public void translate(Language language) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", language.getLocale());
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", language.getLocale());

        HELP = helpBundle.getString("help");
        TEXT = otherBundle.getString("help.text");
    }
}
