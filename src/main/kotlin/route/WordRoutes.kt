package route

import dto.AddWord
import entity.Word
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import storage.deleteWordById
import storage.getAllWords
import storage.getWordsById
import storage.saveWord

fun Route.wordRouting() {
    route("/word") {
        get {
            call.respond(getAllWords())
        }
        get("{id}") {
            call.respond(getWordsById(call.parameters["id"]))
        }
        post {
            val dto = call.receive<AddWord>()
            val wordToDB = Word(
                en = dto.en,
                ru = dto.ru
            )
            call.respond(saveWord(wordToDB))
        }
        delete("{id}") {
            call.respond(deleteWordById(call.parameters["id"]))
        }
    }
}

fun Application.registerWordRoutes() {
    routing {
        wordRouting()
    }
}
