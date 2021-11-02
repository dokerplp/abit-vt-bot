package bot.controller.commands

import bot.utli.ResourceOperator
import bot.utli.enums.Stickers
import bot.utli.getChatId
import bot.utli.sendMessage
import bot.utli.sendSticker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/start")
class StartCommand : Command {

    @Autowired
    val resourceOperator: ResourceOperator? = null

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {

        val msg = sendMessage(update)
        msg.text = "Welcome to the club buddy!"

        val stc = sendSticker(update)
        stc.sticker = InputFile().setMedia(Stickers.WELCOME_TO_THE_CLUB.toString())

        return arrayOf(msg, stc)
    }


    override fun help(update: Update): String {
        return "/start - welcome to the club buddy..."
    }

    override fun name(): String {
        return "/start"
    }
}