package bot.controller.commands

import bot.utli.sendMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/author")
class AuthorCommand : Command {
    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {

        val msg = sendMessage(update)
        msg.enableMarkdown(true)
        msg.text = help(update)

        return arrayOf(msg)
    }


    override fun help(update: Update): String {
        return "author"
    }

    override fun name(): String {
        return "/author"
    }
}