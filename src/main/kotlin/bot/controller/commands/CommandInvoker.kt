package bot.controller.commands

import bot.utli.ResourceOperator
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class CommandInvoker(
    @Autowired val context: ApplicationContext,
    @Autowired val resourceOperator: ResourceOperator
) {

    private var map: Map<String, Command> = context.getBeansOfType(Command::class.java)

    fun execute(command: String, update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = resourceOperator.getText("invoker.text", getChatId(update))!!
        return map[command]?.execute(update) ?: arrayOf(msg)
    }
}