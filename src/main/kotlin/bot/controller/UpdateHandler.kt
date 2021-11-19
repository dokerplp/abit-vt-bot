package bot.controller

import bot.controller.handler.HandleInvoker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update


@Component
class UpdateHandler(
    @Autowired val invoker: HandleInvoker
) {

    fun newUpdate(update: Update): Array<PartialBotApiMethod<Message>>? {
        if (update.hasMessage()) {
            val message = update.message
            if (message.hasText()) {
                return invoker.handleText(update)
            } else if (message.hasVoice()) {
                return invoker.handleVoice(update)
            }
        } else if (update.hasCallbackQuery()) {
            return invoker.handleCallBack(update)
        }
       return null
    }
}