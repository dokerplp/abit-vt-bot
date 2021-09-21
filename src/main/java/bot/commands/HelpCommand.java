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
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello");

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        for (Command command : bot.availableCommands().values()){
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(command.help());
            List<InlineKeyboardButton> bl = new ArrayList<>();
            bl.add(button);
            buttons.add(bl);
        }

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(buttons);

        sendMessage.setAllowSendingWithoutReply(true);
        sendMessage.setReplyMarkup(keyboard);

        bot.execute(sendMessage);
    }

    @Override
    public String help() {
        return "/help - show list of available commands";
    }
}
