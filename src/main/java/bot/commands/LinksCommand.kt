package bot.commands

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import bot.util.Log
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class LinksCommand(private val bot : AbitVTBot) : Command {

    private val log: Log = Log()

    override fun execute(update: Update) {
        val chatId = getChatId(update)

        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = bot.resourceOperator.getText("links.text", chatId)!!

        val parser = JSONParser()

        val res = javaClass.getResourceAsStream("/links.json")
        try {
            BufferedReader(InputStreamReader(res!!)).use { reader ->
                val links = parser.parse(reader) as JSONArray
                val buttons: MutableList<List<InlineKeyboardButton>> =
                    ArrayList()
                for (job in links) {
                    job as JSONObject
                    val button = InlineKeyboardButton()
                    button.text = job["text"] as String
                    button.url = job["link"] as String
                    val bl: MutableList<InlineKeyboardButton> = ArrayList()
                    bl.add(button)
                    buttons.add(bl)
                }
                val keyboard = InlineKeyboardMarkup(buttons)
                sendMessage.replyMarkup = keyboard
                bot.execute(sendMessage)
            }
        } catch (e: IOException) {
            log.userException(e, update)
        } catch (e: ParseException) {
            log.userException(e, update)
        }
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("links", chatId)!!
    }

    override fun name(): String {
        return "/links"
    }
}