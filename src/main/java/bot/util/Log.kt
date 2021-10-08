package bot.util

import org.apache.logging.log4j.LogManager
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class Log {

    private val logger = LogManager.getLogger()

    fun turnOn() {
        logger.info("The application was launched")
    }

    fun turnOff() {
        logger.info("The application was shut down")
    }

    fun telegramApiException(e: TelegramApiException, update: Update) {
        logger.error(
            "User {} has {} TelegramApiException with stacktrace\n{}",
            CommandsUtil.getChatId(update),
            e,
            e.stackTrace
        )
    }

    fun userException(e: Exception, update: Update) {
        logger.error("User {} has {} exception with stacktrace\n{}", CommandsUtil.getChatId(update), e, e.stackTrace)
    }

    fun userException(e: Exception?) {
        logger.error(e)
    }

    fun appError(e: Exception, update: Update) {
        logger.fatal("User {} has {} exception with stacktrace\n{}", CommandsUtil.getChatId(update), e, e.stackTrace)
    }

    fun databaseConnect(URL: String?, PASS: String?, LOGIN: String?) {
        logger.info("Connecting to database: {}\nlogin:{}\npass:{}", URL, LOGIN, PASS)
    }
}