package bot.controller.commands

import bot.model.controller.LinkController
import bot.utli.ResourceOperator
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component("/links")
class LinksCommand(
    @Autowired val resourceOperator: ResourceOperator,
    @Autowired val linkController: LinkController
) : Command {


    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = resourceOperator.getText("links.text", getChatId(update))!!

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        for (link in linkController.findAll()) {
            val button = InlineKeyboardButton()
            button.text = link.text
            button.url = link.value
            val bl: MutableList<InlineKeyboardButton> = ArrayList()
            bl.add(button)
            buttons.add(bl)
        }
        val keyboard = InlineKeyboardMarkup(buttons)
        msg.replyMarkup = keyboard

        return arrayOf(msg)
    }


    override fun help(update: Update): String {
        return resourceOperator.getHelp("links", getChatId(update))!!
    }

    override fun name(): String {
        return "/links"
    }
}