package bot.util.settings

import bot.util.TextHandler
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class SettingsInvoker(private val textHandler: TextHandler) {

    private val settingMap: MutableMap<String, Setting> = LinkedHashMap()

    fun add(key: String, setting: Setting) {
        settingMap[key] = setting
    }

    fun execute(key: String, update: Update?) {
        val setting = settingMap[key]
        if (setting != null) setting.execute(update) else {
            textHandler.text(key, update)
        }
    }
}