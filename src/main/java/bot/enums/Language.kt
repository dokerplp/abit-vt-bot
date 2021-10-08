package bot.enums

import java.util.*

enum class Language(val locale: Locale, private val lan: String) {
    RU(Locale("ru", "RU"), "RU"),
    EN(Locale.US, "EN");

    override fun toString(): String {
        return lan
    }

    companion object {
        fun convert(lang: String?): Language? {
            return when (lang) {
                "EN" -> EN
                "RU" -> RU
                else -> null
            }
        }
    }
}