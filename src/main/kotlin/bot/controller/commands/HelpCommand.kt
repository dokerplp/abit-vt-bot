package bot.controller.commands

import bot.model.controller.LanguageController
import bot.model.entity.LanguageEntity
import bot.utli.getChatId
import bot.utli.sendMessage
import jdk.nashorn.internal.objects.annotations.Getter
import lombok.Setter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/help")
class HelpCommand : Command {


    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {

        TODO()
    }


    override fun help(chatId: String): String {
        return ""
    }

    override fun name(): String {
        return "/help"
    }
}