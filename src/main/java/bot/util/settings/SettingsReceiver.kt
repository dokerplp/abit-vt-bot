package bot.util.settings

import bot.run.AbitVTBot

class SettingsReceiver(private val bot: AbitVTBot) {

    fun setSettings(invoker: SettingsInvoker) {
        val settings: Settings = Settings(bot)
        invoker.add("⚙️ Settings \uD83D\uDEE0", settings)
        invoker.add("⚙️ Настройки \uD83D\uDEE0", settings)
        invoker.add("⚙️ En \uD83C\uDDFA\uD83C\uDDF8", EN(bot))
        invoker.add("⚙️ Ru \uD83C\uDDF7\uD83C\uDDFA", RU(bot))
    }
}