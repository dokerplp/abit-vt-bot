package dokerplp.bot.controller.handler

import dokerplp.bot.utli.enums.Stickers
import dokerplp.bot.utli.sendSticker
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("voiceHandler")
class VoiceHandler : Handler {

    /**
     * Easter egg
     *
     * @param update
     * @return
     */
    override fun handle(update: Update): Array<PartialBotApiMethod<Message>>? {
        val stc = sendSticker(update)
        stc.sticker = InputFile().setMedia(Stickers.VOICE.toString())
        return arrayOf(stc)
    }
}