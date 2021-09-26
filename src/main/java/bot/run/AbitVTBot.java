package bot.run;

import bot.commands.Command;
import bot.commands.Invoker;
import bot.commands.Receiver;
import bot.util.BotUpdatesHandler;
import bot.util.Log;
import bot.util.database.DatabaseConnection;
import bot.util.database.SqlFunctions;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class AbitVTBot extends TelegramLongPollingBot {

    private final Log log = new Log();

    private final BotUpdatesHandler updatesHandler;
    private final Invoker invoker;
    private final SqlFunctions sqlFunctions;

    public AbitVTBot() {
        this.updatesHandler = new BotUpdatesHandler(this);
        this.invoker = new Invoker(this);
        Receiver receiver = new Receiver(this);
        this.sqlFunctions = new SqlFunctions(new DatabaseConnection().getConnection());
        receiver.setCommands(invoker);

        log.turnOn();
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


    @Override
    public void onUpdateReceived(Update update) {
        try {
            updatesHandler.newUpdate(update);
        } catch (TelegramApiException e) {
            log.telegramApiException(e, update);
        } catch (Exception e){
            log.appError(e, update);
        }
    }

    public void invoke(String command, Update update) throws TelegramApiException {
        invoker.execute(command, update);
    }

    public Map<String, Command> availableCommands() {
        return invoker.getCommandMap();
    }

    public SqlFunctions getSql(){
        return sqlFunctions;
    }


}
