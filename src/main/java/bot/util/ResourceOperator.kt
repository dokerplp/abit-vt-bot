package bot.util

import bot.run.AbitVTBot
import java.util.*

class ResourceOperator(private val bot: AbitVTBot) {
    fun getText(key: String, chatId: String): String? {
        val otherBundle: ResourceBundle =
            ResourceBundle.getBundle("other", bot.sqlFunctions.selectLanguage(chatId)!!.locale)
        return otherBundle.getString(key)
    }

    fun getHelp(key: String, chatId: String): String? {
        val helpBundle: ResourceBundle =
            ResourceBundle.getBundle("help", bot.sqlFunctions.selectLanguage(chatId)!!.locale)
        return helpBundle.getString(key)
    }
}