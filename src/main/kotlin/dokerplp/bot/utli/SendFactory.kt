package dokerplp.bot.utli

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

fun sendMessage(update: Update) : SendMessage {
    return dokerplp.bot.utli.SendMessage().send(update)
}

fun sendSticker(update: Update) : SendSticker {
    return dokerplp.bot.utli.SendSticker().send(update)
}

fun sendDocument(update: Update) : SendDocument {
    return dokerplp.bot.utli.SendDocument().send(update)
}

internal interface Sendable {
    fun send(update: Update): PartialBotApiMethod<Message>
}

internal class SendMessage : Sendable {
    override fun send(update: Update): SendMessage {
        val msg = SendMessage()
        msg.chatId = getChatId(update).toString()
        return msg
    }
}
internal class SendSticker : Sendable {
    override fun send(update: Update): SendSticker {
        val msg = SendSticker()
        msg.chatId = getChatId(update).toString()
        return msg
    }
}

internal class SendDocument : Sendable {
    override fun send(update: Update): SendDocument {
        val msg = SendDocument()
        msg.chatId = getChatId(update).toString()
        return msg
    }
}