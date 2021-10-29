package bot.utli

import org.telegram.telegrambots.meta.api.objects.Update

object BotUtil {
    fun getChatId(update: Update): String {
        return update.message?.chatId?.toString() ?: update.callbackQuery.message.chatId.toString()
    }
}