package storage

import com.mongodb.client.AggregateIterable
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import entity.Word
import mongo.Connection
import org.bson.types.ObjectId
import java.util.*

val wordCollection = Connection.connect<Word>("words")

fun getWordById(id: ObjectId?): Word? {
    val word = wordCollection.find(Filters.eq("_id", id)).first()
    val supplier = word.supplierId?.toObjectId()?.let { getSupplierById(it) }
    word.supplier = supplier
    return word
}


fun getWordsByEnglish(word: String) =
    wordCollection.find(Filters.eq("en", word.lowercase(Locale.getDefault()).trim())).into(ArrayList())

fun getWordsByRussian(word: String) =
    wordCollection.find(Filters.eq("ru", word.lowercase(Locale.getDefault()).trim())).into(ArrayList())

fun getRandomWord(): AggregateIterable<Word> = wordCollection.aggregate(listOf(Aggregates.sample(4)))

fun deleteWordById(id: String): Long = wordCollection.deleteOne(Filters.eq("_id", ObjectId(id))).deletedCount

fun getAllWords(): ArrayList<Word> = wordCollection.find().into(ArrayList())

fun saveWord(word: Word) = wordCollection.insertOne(word).insertedId?.asObjectId()?.value.let {
    getWordById(it)
}

fun updateWord(word: Word) = wordCollection.replaceOne(Filters.eq("_id", word.id), word).matchedCount
