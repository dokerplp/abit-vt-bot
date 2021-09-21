package bot.util;

import org.telegram.telegrambots.meta.api.interfaces.Validable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SendMessageHandler {
    public static Validable send(Update update){
        Message message = update.getMessage();
        if (message.hasText()){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText(message.getText());
            return sendMessage;
        }
        return null;
    }
}
