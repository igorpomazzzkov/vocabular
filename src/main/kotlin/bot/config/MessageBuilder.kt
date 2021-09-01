package bot.config

import bot.button.ButtonCommand
import bot.text.CommandExecutor
import org.telegram.telegrambots.meta.api.objects.Update

class MessageBuilder {
    fun createMessageHandler(update: Update?): MessageHandler {
        update?.takeIf { it.hasMessage().and(update.message.hasText()) }?.let {
            return CommandExecutor()
        }
        return ButtonCommand()
    }
}