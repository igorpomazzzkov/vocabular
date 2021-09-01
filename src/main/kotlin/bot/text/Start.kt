package bot.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class Start : CommandHandler {
    override fun response(update: Update): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.chatId = update.message.chatId.toString()
        sendMessage.text = "Привет, я бот обучающий тебя новым английским словам!"
        return sendMessage
    }
}