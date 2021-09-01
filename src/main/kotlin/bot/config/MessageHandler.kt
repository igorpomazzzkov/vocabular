package bot.config

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface MessageHandler {
    fun execute(update: Update): SendMessage
}