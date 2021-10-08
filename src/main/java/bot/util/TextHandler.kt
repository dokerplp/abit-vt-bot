package bot.util

import bot.enums.Stickers
import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import bot.util.settings.SettingsInvoker
import bot.util.settings.SettingsReceiver
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class TextHandler(private val bot: AbitVTBot) {
    private val invoker: SettingsInvoker = SettingsInvoker(this)
    private val receiver: SettingsReceiver = SettingsReceiver(bot)

    init {
        receiver.setSettings(invoker)
    }

    fun analyze(text: String, update: Update?) {
        if (text.startsWith("⚙️")) {
            invoker.execute(text, update)
        } else text(text, update)
    }

    fun text(text: String, update: Update?) {
        val chatId = getChatId(update!!)
        val sticker = SendSticker()
        sticker.chatId = chatId
        sticker.sticker = InputFile().setMedia(Stickers.KOTIKI.toString())
        val message = SendMessage()
        message.chatId = chatId
        message.text = "I don't know answer on question:\n\"$text\""
        bot.execute(message)
        bot.execute(sticker)
    }
}