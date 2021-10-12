package bot.commands

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.util.*

class PlanCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        val chatId = getChatId(update)

        val sendDocument = SendDocument()
        sendDocument.chatId = chatId

        val plans = InputFile()
        plans.setMedia("https://eduold-prod.itmo.dev/file/subspec/4285/09.03.01_kompyuternye_sistemy_i_tehnologii_14549.pdf")
        sendDocument.document = plans

        sendDocument.allowSendingWithoutReply = true
        bot.execute(sendDocument)

        plans.setMedia("https://eduold-prod.itmo.dev/file/subspec/4290/09.03.04_44.03.04_kompyuternye_tehnologii_v_dizayne_14546_14547.pdf")

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        val button = InlineKeyboardButton()
        button.text = bot.resourceOperator.getText("plan.text", chatId)!!
        button.url = "https://docs.google.com/spreadsheets/d/1NlrnPsPksHXzEHFnSHtUtbJg5AENXx6pVFBevHIg-4k/edit#gid=1824976275"

        val bl: MutableList<InlineKeyboardButton> = ArrayList()
        bl.add(button)
        buttons.add(bl)

        val keyboard = InlineKeyboardMarkup(buttons)

        sendDocument.replyMarkup = keyboard

        bot.execute(sendDocument)
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("plan", chatId)!!
    }

    override fun name(): String {
        return "/plan"
    }
}