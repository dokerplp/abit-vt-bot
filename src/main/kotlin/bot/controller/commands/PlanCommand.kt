package bot.controller.commands

import bot.utli.ResourceOperator
import bot.utli.enums.Links
import bot.utli.sendDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component("/plan")
class PlanCommand(
    @Autowired val resourceOperator: ResourceOperator
) : Command {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val sdm = sendDocument(update)
        val plan1 = InputFile()
        plan1.setMedia(Links.PLAN_IVT.toString())
        sdm.document = plan1

        sdm.allowSendingWithoutReply = true

        val sdm2 = sendDocument(update)
        val plan2 = InputFile()
        sdm2.document = plan2
        plan2.setMedia(Links.PLAN_SPPO.toString())

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        val button = InlineKeyboardButton()
        button.text = resourceOperator.getText("plan.text", update)
        button.url = Links.PLAN_CUSTOM.toString()

        val bl: MutableList<InlineKeyboardButton> = ArrayList()
        bl.add(button)
        buttons.add(bl)

        val keyboard = InlineKeyboardMarkup(buttons)

        sdm2.replyMarkup = keyboard

        return arrayOf(sdm, sdm2)
    }


    override fun help(update: Update): String {
        return resourceOperator.getHelp("plan", update)
    }

    override fun name(): String {
        return "/plan"
    }
}