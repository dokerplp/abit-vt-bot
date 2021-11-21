package dokerplp.bot.controller.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class HandleInvoker(@Autowired final val context: ApplicationContext) {

    private var map: Map<String, Handler> = context.getBeansOfType(Handler::class.java)

    fun handleText(update: Update): Array<PartialBotApiMethod<Message>>? {
        return map["textHandler"]?.handle(update)
    }

    fun handleCallBack(update: Update): Array<PartialBotApiMethod<Message>>? {
        return map["callBackHandler"]?.handle(update)
    }

    fun handleVoice(update: Update) : Array<PartialBotApiMethod<Message>>? {
        return map["voiceHandler"]?.handle(update)
    }
}