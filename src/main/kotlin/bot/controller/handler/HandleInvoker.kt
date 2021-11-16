package bot.controller.handler

import bot.core.Bot
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class HandleInvoker(@Autowired val context: ApplicationContext) {
    private var map: Map<String, Handler> = context.getBeansOfType(Handler::class.java)

    fun handleText(msg: Message, update: Update) : Array<PartialBotApiMethod<Message>>? {
        return map["textHandler"]?.handle(msg,update)
    }

    fun handleCallBack(text: String, update: Update) : Array<PartialBotApiMethod<Message>>? {
        return (map["textHandler"] as TextHandler).callBackHandle(text, update)
    }

    fun handleVoice(msg: Message, update: Update) : Array<PartialBotApiMethod<Message>>? {
        return map["voiceHandler"]?.handle(msg, update)
    }
}