package bot.run;

import bot.commands.Command;
import bot.commands.Invoker;
import bot.commands.Receiver;
import bot.util.BotUpdatesHandler;
import bot.util.database.DatabaseConnection;
import bot.util.database.SqlFunctions;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class AbitVTBot extends TelegramLongPollingBot {

    //private Language language = Language.EN;
    private final BotUpdatesHandler updatesHandler;
    private final Invoker invoker;
    private final SqlFunctions sqlFunctions;

    //private final Translator translator = new Translator();

    public AbitVTBot() {
        this.updatesHandler = new BotUpdatesHandler(this);
        this.invoker = new Invoker(this);
        Receiver receiver = new Receiver(this);
        this.sqlFunctions = new SqlFunctions(new DatabaseConnection().getConnection());

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

    public Map<String, Command> availableCommands() {
        return invoker.getCommandMap();
    }

//    public Language getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(Language language) {
//        this.language = language;
//    }
//
//    public Translator getTranslator() {
//        return translator;
//    }

    public SqlFunctions getSql(){
        return sqlFunctions;
    }


}
