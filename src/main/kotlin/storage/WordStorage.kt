package storage

import com.mongodb.client.AggregateIterable
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import entity.Word
import mongo.Connection
import org.bson.types.ObjectId
import java.util.*

val collection = Connection.connectWord("words")

fun getWordsById(id: String?): ArrayList<Word> =
    collection.find(Filters.eq("_id", ObjectId(id))).into(ArrayList())

fun getWordsByEnglish(word: String) =
    collection.find(Filters.eq("en", word.lowercase(Locale.getDefault()).trim())).into(ArrayList())

fun getWordsByRussian(word: String) =
    collection.find(Filters.eq("ru", word.lowercase(Locale.getDefault()).trim())).into(ArrayList())

fun getRandomWord(): AggregateIterable<Word> = collection.aggregate(listOf(Aggregates.sample(4)))

fun deleteWordById(id: String?): Long = collection.deleteOne(Filters.eq("id", id)).deletedCount

fun getAllWords(): ArrayList<Word> = collection.find().into(ArrayList())

fun saveWord(word: Word) = collection.insertOne(word)
