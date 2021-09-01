package bot.button

import bot.config.MessageHandler
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class ButtonCommand : MessageHandler {
    override fun execute(update: Update): SendMessage {
        TODO("Not yet implemented")
    }
}