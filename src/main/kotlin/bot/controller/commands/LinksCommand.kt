package bot.controller.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/links")
class LinksCommand : Command {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        TODO("Not yet implemented")
    }


    override fun help(update: Update): String {
        return "link"
    }

    override fun name(): String {
        return "/links"
    }
}