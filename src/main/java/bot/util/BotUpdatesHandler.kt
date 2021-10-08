package bot.util

import bot.enums.Stickers
import bot.run.AbitVTBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.UpdatesHandler

class BotUpdatesHandler(private val bot: AbitVTBot) : UpdatesHandler {

    private val textHandler:TextHandler = TextHandler(bot)

    fun newUpdate(update: Update) {
        if (update.hasMessage()) {
            val message = update.message
            if (message.hasText()) {
                val text = message.text.split(" ".toRegex()).toTypedArray()[0]
                if (text.startsWith("/")) bot.inv(text, update) else textHandler.analyze(message.text, update)
            } else if (message.hasVoice()) {
                Stickers.VOICE.sendSticker(bot, update)
            } else if (message.hasSticker()) {
                println(message.sticker.fileId)
            }
        } else if (update.hasCallbackQuery()) {
            val text = update.callbackQuery.data
            if (text.startsWith("/")) bot.inv(text, update)
        }
    }
}