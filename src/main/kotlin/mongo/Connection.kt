package mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import entity.Word
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider


object Connection {
    var mongoClient: MongoClient

    init {
        val codecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )
        val mongoClientSettings: MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString("mongodb://root:12345@localhost:27017"))
            .codecRegistry(codecRegistry)
            .build()
        mongoClient = MongoClients.create(mongoClientSettings)
    }

    inline fun <reified R> connect(collectionName: String): MongoCollection<R> =
        mongoClient.getDatabase("english").getCollection(collectionName, R::class.java)

    fun connectWord(collectionName: String): MongoCollection<Word> =
        mongoClient.getDatabase("english").getCollection(collectionName, Word::class.java)

    fun close() = mongoClient.close()
}