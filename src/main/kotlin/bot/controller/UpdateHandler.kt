package bot.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Scope
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update


@Component("handler")
class UpdateHandler {

    fun newUpdate(update: Update): PartialBotApiMethod<Message> {
        if (update.hasMessage()){
            val message = update.message
            if (message.hasText()) {
                val sendMessage = SendMessage()
                sendMessage.chatId = message.chatId.toString()
                sendMessage.text = "Welcome to the club buddy!"
                return sendMessage
            }
        }
       return SendMessage()
    }
}