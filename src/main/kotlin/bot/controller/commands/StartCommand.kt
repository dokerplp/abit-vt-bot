package bot.controller.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/start")
class StartCommand : Command {
    override fun execute(update: Update): ArrayList<PartialBotApiMethod<Message>>? {
        TODO("Not yet implemented")
    }


    override fun help(chatId: String): String {
        return ""
    }

    override fun name(): String {
        return "/start"
    }
}