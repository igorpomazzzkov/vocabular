package route

import dto.AddSupplier
import dto.UpdateSupplier
import entity.Supplier
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bson.types.ObjectId
import storage.deleteSupplier
import storage.getAllSuppliers
import storage.getSupplierById
import storage.saveSupplier
import storage.updateSupplier

val contentType: ContentType = ContentType("application", "json")

fun Route.supplierRoute() {
    route("/api/supplier") {
        get {
            call.respond(getAllSuppliers())
        }
        get("{id}") {
            val id = call.parameters["id"]
            getSupplierById(ObjectId(id))?.let {
                call.respond(it)
            } ?: run {
                call.respondText("Supplier not found with id $id", contentType, status = HttpStatusCode.BadRequest)
            }
        }
        post {
            call.receive<AddSupplier>().let {
                Supplier(
                    name = it.name,
                    description = it.description
                )
            }.let { supplier ->
                saveSupplier(supplier)?.let { call.respond(it) } ?: run {
                    call.respondText("Supplier did not add", contentType, status = HttpStatusCode.InternalServerError)
                }
            }
        }
        put {
            val updated = call.receive<UpdateSupplier>()
            getSupplierById(
                ObjectId(
                    updated.id
                )
            )?.let { supplier ->
                supplier.name = updated.name ?: supplier.name
                supplier.description = updated.description
                updateSupplier(supplier)?.let {
                    call.respond(it)
                } ?: run {
                    call.respondText(
                        "Supplier did not update",
                        contentType,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            } ?: run {
                call.respondText("Supplier did not found", contentType, status = HttpStatusCode.BadRequest)
            }
        }
        delete("{id}") {
            deleteSupplier(ObjectId(call.parameters["id"])).takeIf { it == 1L }?.let {
                call.respond(it)
            } ?: run {
                call.respondText("Supplier has not deleted")
            }
        }
    }
}

fun Application.registerSupplierRoute() {
    routing {
        supplierRoute()
    }
}