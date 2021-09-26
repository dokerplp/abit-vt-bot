package bot.commands;

import bot.run.AbitVTBot;
import bot.util.CommandsUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelpCommand implements Command {

    private String getTEXT(String chatId){
        ResourceBundle otherBundle = ResourceBundle.getBundle("other", bot.getSql().selectLanguage(chatId).getLocale());
        return otherBundle.getString("help.text");
    }

    private final AbitVTBot bot;

    public HelpCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId = CommandsUtil.getChatId(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getTEXT(chatId));

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        for (Command command : bot.availableCommands().values()){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(command.help(chatId));
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
    public String help(String chatId) {
        ResourceBundle helpBundle = ResourceBundle.getBundle("help", bot.getSql().selectLanguage(chatId).getLocale());
        return helpBundle.getString("help");
    }

    @Override
    public String name() {
        return "/help";
    }

}
