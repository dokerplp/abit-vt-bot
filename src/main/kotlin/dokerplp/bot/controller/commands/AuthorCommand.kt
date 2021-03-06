package dokerplp.bot.controller.commands

import dokerplp.bot.utli.ResourceOperator
import dokerplp.bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/author")
class AuthorCommand(
    @Autowired val resourceOperator: ResourceOperator
) : Command {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.enableMarkdown(true)
        msg.text = resourceOperator.getText("author.greeting", update)
        return arrayOf(msg)
    }

    override fun help(update: Update): String {
        return resourceOperator.getHelp("author", update)
    }

    override fun name(): String {
        return "/author"
    }
}