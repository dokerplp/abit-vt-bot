package bot.controller.settings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class SettingInvoker(@Autowired context: ApplicationContext) {
    private var map: Map<String, Setting> = context.getBeansOfType(Setting::class.java)

    fun execute(command: String, update: Update) : Array<PartialBotApiMethod<Message>>? {
        return map[command]?.execute(update)
    }
}