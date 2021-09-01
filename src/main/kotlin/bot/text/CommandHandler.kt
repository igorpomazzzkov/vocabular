package bot.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface CommandHandler {
    fun response(update: Update): SendMessage
}