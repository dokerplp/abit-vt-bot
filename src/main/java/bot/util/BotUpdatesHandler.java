package bot.util;

import bot.run.AbitVTBot;
import org.telegram.telegrambots.meta.api.interfaces.Validable;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.UpdatesHandler;

import java.util.Optional;

public class BotUpdatesHandler implements UpdatesHandler {

    private final AbitVTBot bot;

    public BotUpdatesHandler(AbitVTBot bot) {
        this.bot = bot;
    }

    public void newUpdate(Update update){
        if (update.hasMessage()){
            Message message = update.getMessage();
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()){
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());

            }
        }
    }
}
