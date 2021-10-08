package bot.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.ZonedDateTime

object CommandsUtil {
    fun getChatId(update: Update): String {
        return update.message?.chatId?.toString() ?: update.callbackQuery.message.chatId.toString()
    }

    fun notifyAdmin(e: Exception, update: Update) {
        val chatId = System.getenv("ADMIN")
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        if (update.message.text != null) sendMessage.text = String.format(
            "User {%s} has exception {%s} on request {%s} at {%s}",
            getChatId(update),
            e.toString(),
            update.message.text,
            ZonedDateTime.now()
        ) else sendMessage.text =
            String.format(
                "User {%s} has exception {%s} on request {%s} at {%s}",
                getChatId(update),
                e.toString(),
                update.message.toString(),
                ZonedDateTime.now()
            )
    }
}