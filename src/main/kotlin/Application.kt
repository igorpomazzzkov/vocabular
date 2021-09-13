import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import org.slf4j.event.Level
import route.registerSupplierRoute
import route.registerWordRoutes
import java.io.InputStream
import java.util.*

class Main

fun main(args: Array<String>) {
    val telegramToken = getProperty("telegram.access.token")
    val telegramUsername = getProperty("telegram.bot.username")
    EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    registerWordRoutes()
    registerSupplierRoute()

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}

fun println(vararg msg: String) {
    msg.forEach {
        if (it == msg.last()) {
            println()
        } else {
            print("$it ")
        }
    }
}

fun getProperty(property: String): String {
    val stream: InputStream = Main::class.java.getResourceAsStream("application.properties")
    val properties: Properties = Properties()
    properties.load(stream)
    val prop = properties.getProperty(property)
    stream.close()
    return prop
}

