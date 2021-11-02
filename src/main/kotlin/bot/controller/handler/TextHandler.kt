package bot.controller.handler

import bot.controller.commands.CommandInvoker
import bot.controller.settings.SettingInvoker
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
class TextHandler(@Autowired val commandInvoker: CommandInvoker, @Autowired val settingInvoker: SettingInvoker) : Handler {
    override fun handle(msg: Message, update: Update): Array<PartialBotApiMethod<Message>>? {
        val text = msg.text.split(" ".toRegex()).toTypedArray()[0]
        return handle(text, update)
    }

    fun handle(text: String, update: Update): Array<PartialBotApiMethod<Message>>? {
        return if (text.startsWith("/"))
            commandInvoker.execute(text, update)
        else if (text.startsWith("⚙️"))
            settingInvoker.execute(text, update)
        else
            default(text, update)
    }

    private fun default(text: String, update: Update): Array<PartialBotApiMethod<Message>>{

        val chatId = update.message.chatId.toString()
        val sticker = SendSticker()
        sticker.chatId = chatId
        sticker.sticker = InputFile().setMedia(Stickers.KOTIKI.toString())
        val message = SendMessage()
        message.chatId = chatId
        message.text = "I don't know answer on question:\n\"$text\""

        return arrayOf(message, sticker)
    }

}