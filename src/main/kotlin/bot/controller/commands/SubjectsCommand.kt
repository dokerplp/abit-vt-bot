package bot.controller.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/subjects")
class SubjectsCommand : Command {
    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        TODO("Not yet implemented")
    }


    override fun help(update: Update): String {
        return "subjects"
    }

    override fun name(): String {
        return "/subjects"
    }
}