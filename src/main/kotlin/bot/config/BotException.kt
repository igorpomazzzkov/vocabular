open class BotException(private val msg: String) : Exception(msg)

class CommandNotFoundException(private val msg: String) : BotException(msg)