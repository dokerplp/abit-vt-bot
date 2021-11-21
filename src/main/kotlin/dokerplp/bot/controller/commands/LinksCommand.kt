package dokerplp.bot.controller.commands

import dokerplp.bot.model.controller.LinkController
import dokerplp.bot.utli.ResourceOperator
import dokerplp.bot.utli.sendMessage
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
        msg.text = resourceOperator.getText("links.text", update)

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        for (link in linkController.findAllByShow(true).sortedBy { it.id }) {
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
        return resourceOperator.getHelp("links", update)
    }

    override fun name(): String {
        return "/links"
    }
}