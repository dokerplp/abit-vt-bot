package bot.controller.commands

import bot.utli.ResourceOperator
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component("/help")
class HelpCommand(
    @Autowired val context: ApplicationContext,
    @Autowired val resourceOperator: ResourceOperator
) : Command {

    private var map: Map<String, Command> = context.getBeansOfType(Command::class.java)


    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {

        val sendMessage = sendMessage(update)
        sendMessage.text = resourceOperator.getText("help.text", getChatId(update))!!

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()

        for (command in map.values) {
            val button = InlineKeyboardButton()
            button.text = command.help(update)
            button.callbackData = command.name()
            val bl: MutableList<InlineKeyboardButton> = ArrayList()
            bl.add(button)
            buttons.add(bl)
        }

        val keyboard = InlineKeyboardMarkup(buttons)

        sendMessage.replyMarkup = keyboard

        return arrayOf(sendMessage)
    }


    override fun help(update: Update): String {
        return resourceOperator.getHelp("help", getChatId(update))!!
    }

    override fun name(): String {
        return "/help"
    }
}