package bot.commands;

import bot.run.AbitVTBot;
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

public class HelpCommand implements Command{

    private final AbitVTBot bot;

    public HelpCommand(AbitVTBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {

        String chatId;

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Hello");

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
        return "/help - show list of available commands";
    }

    @Override
    public String name() {
        return "/help";
    }
}
