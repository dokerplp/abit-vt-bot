package bot.controller.handler

import bot.controller.commands.CommandInvoker
import bot.utli.enums.Stickers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("textHandler")
class TextHandler(@Autowired val commandInvoker: CommandInvoker) : Handler {
    override fun handle(msg: Message, update: Update): ArrayList<PartialBotApiMethod<Message>>? {

        var list: ArrayList<PartialBotApiMethod<Message>>? = null

        val text = msg.text.split(" ".toRegex()).toTypedArray()[0]
        list =
            if (text.startsWith("/"))
                commandInvoker.execute(text, update)
            else if (text.startsWith("⚙️"))
                TODO("return settings")
            else
                default(text, update)

        return list
    }

    private fun default(text: String, update: Update): ArrayList<PartialBotApiMethod<Message>>{

        val list: ArrayList<PartialBotApiMethod<Message>> = ArrayList()

        val chatId = update.message.chatId.toString()
        val sticker = SendSticker()
        sticker.chatId = chatId
        sticker.sticker = InputFile().setMedia(Stickers.KOTIKI.toString())
        val message = SendMessage()
        message.chatId = chatId
        message.text = "I don't know answer on question:\n\"$text\""

        list.add(message)
        list.add(sticker)

        return list
    }

}