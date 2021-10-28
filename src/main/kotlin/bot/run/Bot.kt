package bot.run

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

class Bot : TelegramLongPollingBot() {
    override fun getBotToken(): String {
        return ""
    }

    override fun getBotUsername(): String {
        return ""
    }

    override fun onUpdateReceived(p0: Update?) {

    }
}