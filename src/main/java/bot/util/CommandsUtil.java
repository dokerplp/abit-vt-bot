package bot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.ZonedDateTime;

public class CommandsUtil {
    public static String getChatId(Update update){
        String chatId;
        Message message = update.getMessage();
        if (message != null) chatId = message.getChatId().toString();
        else chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        return chatId;
    }

    public static void notifyAdmin(Exception e, Update update){
        String chatId = System.getenv("ADMIN");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (update.getMessage().getText() != null) sendMessage.setText(String.format("User {%s} has exception {%s} on request {%s} at {%s}", getChatId(update), e.toString(), update.getMessage().getText(), ZonedDateTime.now()));
        else sendMessage.setText(String.format("User {%s} has exception {%s} on request {%s} at {%s}", getChatId(update), e.toString(), update.getMessage().toString(), ZonedDateTime.now()));
    }
}
