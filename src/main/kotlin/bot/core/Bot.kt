package bot.core

import bot.controller.UpdateHandler
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.*
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import javax.annotation.PostConstruct


@Slf4j
@Component
class Bot(
    @Autowired val handler: UpdateHandler
) : TelegramLongPollingBot() {

    private val logger: Logger = LoggerFactory.getLogger(Bot::class.java)

    @Value("\${bot.name}")
    private lateinit var botName: String
    override fun getBotUsername(): String = botName

    @Value("\${bot.token}")
    private lateinit var token: String
    override fun getBotToken(): String = token

    override fun onUpdateReceived(update: Update?) {
        handler.newUpdate(update!!)!!.forEach { run(it) }
    }

    fun run(msg: PartialBotApiMethod<Message>) {
        try {
            when (msg) {
                is SendSticker -> execute(msg)
                is SendMessage -> execute(msg)
                is SendAudio -> execute(msg)
                is SendAnimation -> execute(msg)
                is SendDice -> execute(msg)
                is SendPhoto -> execute(msg)
                is SendDocument -> execute(msg)
                is SendVideo -> execute(msg)
                is SendVoice -> execute(msg)
            }
        } catch (e: TelegramApiException) {
            logger.error("TelegramApiException", e)
        } catch (e: Exception) {
            logger.error("UnknownException", e)
        }
    }
}