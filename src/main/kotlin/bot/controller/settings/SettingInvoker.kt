package bot.controller.settings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class SettingInvoker(
    @Autowired context: ApplicationContext
) {

    private var map: HashMap<String, Setting> = HashMap()

    init {
        map["⚙️Settings\uD83D\uDEE0"] = context.getBean("settings") as Settings
        map["⚙️Настройки\uD83D\uDEE0"] = context.getBean("settings") as Settings
        map["⚙️En\uD83C\uDDFA\uD83C\uDDF8"] = context.getBean("en") as En
        map["⚙️Ru\uD83C\uDDF7\uD83C\uDDFA"] = context.getBean("ru") as Ru
    }

    fun execute(command: String, update: Update): Array<PartialBotApiMethod<Message>>? {
        return map[command]?.execute(update)
    }
}