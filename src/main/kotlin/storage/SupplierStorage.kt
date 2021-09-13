package storage

import com.mongodb.client.model.Filters
import entity.Supplier
import mongo.Connection
import org.bson.types.ObjectId

val supplierCollection = Connection.connect<Supplier>("supplier")

fun getAllSuppliers(): List<Supplier> = supplierCollection.find().into(ArrayList())

fun getSupplierById(id: ObjectId): Supplier? = supplierCollection.find(Filters.eq("_id", id)).first()

fun saveSupplier(supplier: Supplier): Supplier? =
    supplierCollection.insertOne(supplier).insertedId?.asObjectId()?.value?.let {
        getSupplierById(it)
    }

fun updateSupplier(supplier: Supplier): Supplier? =
    supplierCollection.replaceOne(Filters.eq("_id", supplier.objectId), supplier).matchedCount.takeIf { it > 0 }?.let {
        getSupplierById(ObjectId(supplier.objectId!!.id))
    }


fun deleteSupplier(id: ObjectId): Long = supplierCollection.deleteOne(Filters.eq("_id", id)).deletedCount