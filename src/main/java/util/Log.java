package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private final static String INFO = "[INFO]";
    private final static String ERROR = "[ERROR]";
    private final static String MESSAGE = "[MESSAGE]";

    private static String now(){
        LocalDateTime now = LocalDateTime.now();
        return now.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + now.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    private final Logger logger = LogManager.getLogger(Logger.class);

    public void exception(Exception e){
        logger.info("{} {} {}", now(), INFO, e);
    }

    public void handlerException(Exception e, String user, String command){
        logger.error("{} {} {} caused by {} at {} command", now(), ERROR, e, user, command);
    }
}
