package bot.text

enum class Command(val command: String) {
    START("start"), NEW_WORDS("addWord"), TEACH("teach"),
    TRUE("right answer"), FALSE("wrong answer")
}