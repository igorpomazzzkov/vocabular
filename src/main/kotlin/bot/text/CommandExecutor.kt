package bot.text

import CommandNotFoundException
import bot.config.MessageHandler
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class CommandExecutor : MessageHandler {

    override fun execute(update: Update): SendMessage {
        return CommandFactory.getCommandHandler(getResponse(update.message.text)).response(update)
    }

    private fun getResponse(message: String): Command {
        return Command.values().find { command ->
            command.command == Command.values().map {
                it.command
            }.find { it == message.substring(1) }
        }.takeIf { true } ?: run {
            throw CommandNotFoundException("Text.Command $message not founded")
        }
    }
}