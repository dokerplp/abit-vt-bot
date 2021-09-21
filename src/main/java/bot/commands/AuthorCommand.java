package bot.commands;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AuthorCommand implements Command{

    private final AbitVTBot bot;

    public AuthorCommand(AbitVTBot bot) {
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
        sendMessage.setText(toString());
        sendMessage.setParseMode("MarkdownV2");



        bot.execute(sendMessage);
    }

    @Override
    public String help() {
        return "/author - get information about bot author";
    }

    @Override
    public String name() {
        return "/author";
    }

    @Override
    public String toString() {
        return "Hi\uD83D\uDC4B\n" +
                "I'm the author of this bot\uD83E\uDD16\n" +
                "My name is Valerii Butorin, I'm 2nd year student of Informatics and Computer Science (IVT) in ITMO" +
                "My tg is @dokerplp\n\n\n" +
                "[Project on GitHub](https://github.com/dokerplp/abit-vt-bot)";
    }
}
