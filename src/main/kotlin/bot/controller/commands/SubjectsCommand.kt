package bot.controller.commands

import bot.utli.ResourceOperator
import bot.utli.getChatId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/subjects")
class SubjectsCommand(
    @Autowired val resourceOperator: ResourceOperator
) : Command {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        TODO("Not yet implemented")
    }


    override fun help(update: Update): String {
        return resourceOperator.getHelp("subjects", getChatId(update))!!
    }

    override fun name(): String {
        return "/subjects"
    }
}