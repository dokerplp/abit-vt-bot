package bot.controller.settings

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface Setting {
    fun execute(update: Update): Array<PartialBotApiMethod<Message>>?
}