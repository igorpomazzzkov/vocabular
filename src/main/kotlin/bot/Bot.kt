package bot

import bot.config.MessageBuilder
import bot.config.MessageHandler
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import bot.text.CommandExecutor

class Bot(token: String, username: String) : TelegramLongPollingBot() {
    private val TOKEN = token
    private val USERNAME = username

    private val messageBuilder: MessageBuilder = MessageBuilder()

    override fun getBotToken() = this.TOKEN

    override fun getBotUsername() = this.USERNAME

    override fun onUpdateReceived(update: Update) {
        var messageHandler: MessageHandler = messageBuilder.createMessageHandler(update)
        update.let {
            if (update.hasCallbackQuery()) {
                val message = SendMessage()
                message.text = update.callbackQuery.data
                message.chatId = update.callbackQuery.message.chatId.toString()
                execute(message)
                messageHandler = CommandExecutor()
                update.message.text = "/teach"
            }
            val send = messageHandler.execute(update)
            execute(send)
        }
    }
}