package bot.controller.commands

import bot.utli.sendDocument
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.util.ArrayList

@Component("/plan")
class PlanCommand: Command{
    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {

        val sdm = sendDocument(update)
        val plan1 = InputFile()
        plan1.setMedia("https://eduold-prod.itmo.dev/file/subspec/4285/09.03.01_kompyuternye_sistemy_i_tehnologii_14549.pdf")
        sdm.document = plan1

        sdm.allowSendingWithoutReply = true

        val sdm2 = sendDocument(update)
        val plan2 = InputFile()
        sdm2.document = plan2
        plan2.setMedia("https://eduold-prod.itmo.dev/file/subspec/4290/09.03.04_44.03.04_kompyuternye_tehnologii_v_dizayne_14546_14547.pdf")

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        val button = InlineKeyboardButton()
        button.text = "plan"
        button.url = "https://docs.google.com/spreadsheets/d/1NlrnPsPksHXzEHFnSHtUtbJg5AENXx6pVFBevHIg-4k/edit#gid=1824976275"

        val bl: MutableList<InlineKeyboardButton> = ArrayList()
        bl.add(button)
        buttons.add(bl)

        val keyboard = InlineKeyboardMarkup(buttons)

        sdm2.replyMarkup = keyboard

        return arrayOf(sdm, sdm2)
    }


    override fun help(update: Update): String {
        return "plan"
    }

    override fun name(): String {
        return "/plan"
    }
}