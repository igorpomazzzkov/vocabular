package route

import dto.AddWordDto
import dto.WordDto
import entity.Word
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bson.types.ObjectId
import storage.deleteWordById
import storage.getAllWords
import storage.getWordById
import storage.saveWord
import storage.updateWord

fun Route.wordRouting() {
    route("/api/word") {
        get {
            call.respond(getAllWords())
        }
        get("{id}") {
            val id = call.parameters["id"]
            getWordById(ObjectId(id))?.let {
                call.respond(it)
            } ?: run {
                call.respondText("Word not found with id $id", status = HttpStatusCode.BadRequest)
            }
        }
        post {
            val dto = call.receive<AddWordDto>()
            val wordToDB = Word(
                en = dto.en,
                ru = dto.ru,
                supplierId = dto.supplier
            )
            val addedWord: Word? = saveWord(wordToDB)
            addedWord?.let {
                call.respond(it)
            } ?: run {
                call.respondText("Word did not add", status = HttpStatusCode.BadRequest)
            }
        }
        put {
            val dto = call.receive<WordDto>()
            val wordFromDB = getWordById(ObjectId(dto.id))?.apply {
                this.en = dto.en ?: this.en
                this.ru = dto.ru ?: this.ru
                this.attempt = dto.attempt ?: this.attempt
                this.supplierId = dto.supplierId ?: this.supplierId
            }
            wordFromDB?.let { word ->
                updateWord(word).takeIf { it == 1L }?.let {
                    call.respond(wordFromDB)
                }
            } ?: run {
                call.respondText("Word did not update id ${dto.id}", status = HttpStatusCode.BadRequest)
            }
        }
        delete("{id}") {
            val id = call.parameters["id"]
            id?.let {
                call.respond(deleteWordById(id))
            } ?: run {
                call.respondText("Id not found", status = HttpStatusCode.BadRequest)
            }
        }
    }
}

fun Application.registerWordRoutes() {
    routing {
        wordRouting()
    }
}
