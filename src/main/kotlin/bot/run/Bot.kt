package bot.run

import jdk.nashorn.internal.objects.annotations.Getter
import jdk.nashorn.internal.objects.annotations.Setter
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class Bot : TelegramLongPollingBot() {

    @Value("\${bot.name}")
    private val botName: String = ""
    override fun getBotUsername(): String = botName

    @Value("\${bot.token}")
    private val token: String = ""
    override fun getBotToken(): String = token

    override fun onUpdateReceived(update: Update?) {
        val chatId = update!!.message.chatId.toString()

        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = "Welcome to the club buddy!"

        execute(sendMessage)
    }
}