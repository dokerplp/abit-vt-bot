package bot.controller.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class CommandInvoker(@Autowired final val context: ApplicationContext) {
    private var map: Map<String, Command> = context.getBeansOfType(Command::class.java)

    fun execute(command: String, update: Update) : ArrayList<PartialBotApiMethod<Message>>? {
        return map[command]?.execute(update)
    }
}