package bot.controller.commands

import bot.model.controller.LanguageController
import bot.model.controller.SubjectController
import bot.utli.ResourceOperator
import bot.utli.enums.Language
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component("/subjects")
class SubjectsCommand(
    @Autowired val languageController: LanguageController,
    @Autowired val resourceOperator: ResourceOperator,
    @Autowired val subjectController: SubjectController
) : Command {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = resourceOperator.getText("subjects.text", update)

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        for (sub in subjectController.findAll()) {
            val button = InlineKeyboardButton()
            when ((languageController.getById(getChatId(update))!!.language)) {
                Language.EN -> button.text = sub.name.en
                Language.RU -> button.text = sub.name.ru
            }
            button.callbackData = "sub:" + sub.id
            val bl: MutableList<InlineKeyboardButton> = ArrayList()
            bl.add(button)
            buttons.add(bl)
        }
        val keyboard = InlineKeyboardMarkup(buttons)
        msg.replyMarkup = keyboard

        return arrayOf(msg)
    }


    override fun help(update: Update): String {
        return resourceOperator.getHelp("subjects", update)
    }

    override fun name(): String {
        return "/subjects"
    }
}