package bot.utli.enums



import java.util.*


enum class Language(val locale: Locale, val value: String){
    RU(Locale("ru", "RU"), "RU"),
    EN(Locale.US, "EN");

    override fun toString(): String {
        return value
    }
}



