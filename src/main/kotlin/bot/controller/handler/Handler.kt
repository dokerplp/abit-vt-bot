package bot.controller.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
interface Handler {
    fun handle(msg: Message, update: Update) : Array<PartialBotApiMethod<Message>>?
}