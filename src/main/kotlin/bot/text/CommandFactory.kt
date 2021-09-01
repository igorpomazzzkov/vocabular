package bot.text

object CommandFactory {
    fun getCommandHandler(command: Command): CommandHandler {
        return when (command) {
            Command.START -> Start()
            Command.TEACH -> Teach()
            else -> Error()
        }
    }
}