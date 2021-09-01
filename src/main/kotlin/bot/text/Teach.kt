package bot.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import storage.getRandomWord
import kotlin.random.Random


class Teach : CommandHandler {
    override fun response(update: Update) = getTeachWord(update)

    companion object {
        const val wrongAnswer: String = "*НЕВЕРНО!* \n Правильный ответ %s"
        const val rightAnswer: String = "*Верно*"


        fun getTeachWord(update: Update): SendMessage {
            val sendMessage: SendMessage = SendMessage()
            sendMessage.chatId = update.message.chatId.toString()
            val inlineKeyboardMarkup: InlineKeyboardMarkup = InlineKeyboardMarkup()
            val keyboardButtonsRow: MutableList<InlineKeyboardButton> = mutableListOf()
            val randomNumber = Random.nextInt(0, 4)
            getRandomWord().forEachIndexed { index, it ->
                val button = InlineKeyboardButton()
                button.text = "text"
                button.callbackData = wrongAnswer
                if (index == randomNumber) {
                    sendMessage.text = "Выберите перевод слова - *${it}*"
                    button.callbackData = rightAnswer
                }
                keyboardButtonsRow.add(button)
            }
            val rowList: MutableList<List<InlineKeyboardButton>> = mutableListOf()
            rowList.add(keyboardButtonsRow)
            inlineKeyboardMarkup.keyboard = rowList
            sendMessage.replyMarkup = inlineKeyboardMarkup
            sendMessage.parseMode = "Markdown"
            return sendMessage
        }
    }
}