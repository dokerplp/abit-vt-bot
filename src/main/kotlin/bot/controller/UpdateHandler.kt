package bot.controller

import bot.controller.handler.HandleInvoker
import org.springframework.beans.factory.annotation.Autowired
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


@Component
class UpdateHandler(@Autowired val invoker: HandleInvoker) {

    fun newUpdate(update: Update): Array<PartialBotApiMethod<Message>>? {
        if (update.hasMessage()){
            val message = update.message
            if (message.hasText()) {
                return invoker.handleText(message, update)
            }
            else if (message.hasVoice()){
                return invoker.handleVoice(message, update)
            }
        }
       return null
    }
}