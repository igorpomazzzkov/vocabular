package bot.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class Error : CommandHandler {
    override fun response(update: Update): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.chatId = update.message.chatId.toString()
        sendMessage.text = "Command not founded"
        return sendMessage
    }
}