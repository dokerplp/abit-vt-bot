package bot.utli

import org.telegram.telegrambots.meta.api.objects.Update

fun getChatId(update: Update): Long {
    return update.message?.chatId ?: update.callbackQuery.message.chatId
}
