package bot.util.settings

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

interface Setting {
    fun execute(update: Update?)
}