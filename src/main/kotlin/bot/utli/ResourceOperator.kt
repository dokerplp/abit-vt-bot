package bot.utli

import bot.model.controller.LanguageController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ResourceOperator(
    @Autowired val languageController: LanguageController
) {

    fun getText(key: String, chatId: Long): String? {
        val otherBundle: ResourceBundle =
            ResourceBundle.getBundle("other", languageController.getById(chatId).language.locale)
        return otherBundle.getString(key)
    }

    fun getHelp(key: String, chatId: Long): String? {
        val helpBundle: ResourceBundle =
            ResourceBundle.getBundle("help", languageController.getById(chatId).language.locale)
        return helpBundle.getString(key)
    }
}