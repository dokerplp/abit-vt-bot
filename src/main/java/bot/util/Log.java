package bot.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Log {

    private static final Logger logger = LogManager.getLogger();

    public void turnOn(){
        logger.info("The application was launched");
    }

    public void turnOff(){
        logger.info("The application was shut down");
    }

    public void telegramApiException(TelegramApiException e, Update update){
        logger.error("User {} has {} TelegramApiException with stacktrace\n{}", CommandsUtil.getChatId(update), e, e.getStackTrace());
    }

    public void userException(Exception e, Update update){
        logger.error("User {} has {} exception with stacktrace\n{}", CommandsUtil.getChatId(update), e, e.getStackTrace());
    }

    public void userException(Exception e){
        logger.error(e);
    }

    public void appError(Exception e, Update update){
        logger.fatal("User {} has {} exception with stacktrace\n{}", CommandsUtil.getChatId(update), e, e.getStackTrace());
    }

    public void databaseConnect(String URL, String PASS, String LOGIN){
        logger.info("Connecting to database: {}\nlogin:{}\npass:{}", URL, LOGIN, PASS);
    }

}
