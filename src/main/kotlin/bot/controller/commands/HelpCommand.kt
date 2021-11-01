package bot.controller.commands

import bot.model.controller.LanguageController
import bot.model.entity.LanguageEntity
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

    @Autowired
    val languageController: LanguageController? = null


    override fun execute(update: Update): ArrayList<PartialBotApiMethod<Message>>? {

        val list: ArrayList<PartialBotApiMethod<Message>> = ArrayList()

        val msg: SendMessage = SendMessage()
        msg.chatId = update.message.chatId.toString()
        msg.text = languageController!!.getById(update.message.chatId.toInt()).language

        list.add(msg)

        return list
    }


    override fun help(chatId: String): String {
        return ""
    }

    override fun name(): String {
        return "/help"
    }
}