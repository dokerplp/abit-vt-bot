package bot.controller.handler

import bot.controller.commands.CommandInvoker
import bot.controller.settings.SettingInvoker
import bot.utli.enums.Stickers
import bot.utli.sendMessage
import bot.utli.sendSticker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("textHandler")
class TextHandler(
    @Autowired val commandInvoker: CommandInvoker,
    @Autowired val settingInvoker: SettingInvoker,
    @Autowired val requestAnalyzer: RequestAnalyzer
) : Handler {

    override fun handle(update: Update): Array<PartialBotApiMethod<Message>>? {
        val text = update.message.text
        return textHandle(text, update)
    }

    /**
     * Checking text
     * If it is command or setting
     *
     * @param text
     * @param update
     * @return
     */
    fun handle(text: String, update: Update): Array<PartialBotApiMethod<Message>>? {
        return if (text.startsWith("/"))
            commandInvoker.execute(text, update)
        else if (text.startsWith("⚙️"))
            settingInvoker.execute(text, update)
        else
            return null
    }

    fun textHandle(text: String, update: Update): Array<PartialBotApiMethod<Message>>? {
        return handle(text.split(" ".toRegex()).toTypedArray()[0], update) ?: requestAnalyzer.analyze(text, update) ?: default(text, update)
    }

    /**
     * Default answer
     *
     * @param text
     * @param update
     * @return
     */
    private fun default(text: String, update: Update): Array<PartialBotApiMethod<Message>>{

        val sticker = sendSticker(update)
        sticker.sticker = InputFile().setMedia(Stickers.KOTIKI.toString())
        val message = sendMessage(update)
        message.text = "I don't know answer on question:\n\"$text\""

        return arrayOf(message, sticker)
    }

}