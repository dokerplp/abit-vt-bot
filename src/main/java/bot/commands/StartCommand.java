package bot.commands;

import bot.enums.Stickers;
import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements Command {

    private String TEXT;

    private final AbitVTBot bot;

    public StartCommand(AbitVTBot bot) {
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
        sendMessage.setText("Welcome to the club buddy!");


        Stickers.WELCOME_TO_THE_CLUB.sendSticker(bot, update);

        bot.execute(sendMessage);
    }

    @Override
    public String help(Long chatId) {
        return "/start - welcome to the club buddy...";
    }

    @Override
    public String name() {
        return "/start";
    }
}
