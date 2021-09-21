package bot.run;

import bot.commands.Invoker;
import bot.commands.Receiver;
import bot.util.BotUpdatesHandler;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.interfaces.Validable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AbitVTBot extends TelegramLongPollingBot {

    private final BotUpdatesHandler updatesHandler;
    private final Invoker invoker;
    private final Receiver receiver;

    public AbitVTBot() {
        this.updatesHandler = new BotUpdatesHandler(this);
        this.invoker = new Invoker(this);
        this.receiver = new Receiver(this);

        receiver.setCommands(invoker);
    }

    public static void main(String[] args) throws TelegramApiException {
        AbitVTBot bot = new AbitVTBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        updatesHandler.newUpdate(update);
    }

    public void invoke(String command, Update update) throws TelegramApiException {
        invoker.execute(command, update);
    }
}
