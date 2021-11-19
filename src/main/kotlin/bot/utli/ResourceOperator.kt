package bot.utli

import bot.model.controller.LanguageController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.*

@Component
class ResourceOperator(
    @Autowired val languageController: LanguageController
) {

    fun getText(key: String, update: Update): String {
        val otherBundle: ResourceBundle =
            ResourceBundle.getBundle("other", languageController.getById(getChatId(update)).language.locale)
        return otherBundle.getString(key) ?: "null"
    }

    fun getHelp(key: String, update: Update): String {
        val helpBundle: ResourceBundle =
            ResourceBundle.getBundle("help", languageController.getById(getChatId(update)).language.locale)
        return helpBundle.getString(key) ?: "null"
    }
}