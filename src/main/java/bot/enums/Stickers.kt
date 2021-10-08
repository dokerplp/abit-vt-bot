package bot.enums

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import java.io.Serializable

enum class Stickers(private val id: String) : Serializable {
    WELCOME_TO_THE_CLUB("CAACAgIAAxkBAAOdYUo0m_syBRShgkKxCTjJqPWLC14AAh8AA_HzcxPzDm9d0wYLCiEE"),
    VOICE("CAACAgIAAxkBAAIBMWFKc0fe3iVLjOG5iBrXuYb1e6EjAAIXAAOr26Y545BSbBihquIhBA"),
    KOTIKI("CAACAgIAAxkBAAICp2FQwwXFjFrrzyHbDBrTQprgQWiQAAIrAAP2zkAb8nPTZBjynH8hBA");

    override fun toString(): String {
        return id
    }

    fun sendSticker(bot: AbitVTBot, update: Update?) {
        val chatId = getChatId(update!!)
        val sendSticker = SendSticker()
        sendSticker.chatId = chatId
        sendSticker.sticker = InputFile().setMedia(toString())
        bot.execute(sendSticker)
    }
}