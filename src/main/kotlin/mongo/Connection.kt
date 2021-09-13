package mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import entity.VocabularIdCodec
import getProperty
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider


object Connection {
    var mongoClient: MongoClient

    var codecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromCodecs(VocabularIdCodec()),
        fromProviders(PojoCodecProvider.builder().automatic(true).build())
    )

    init {
        val mongoClientSettings: MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(getProperty("mongodb.url")))
            .build()
        mongoClient = MongoClients.create(mongoClientSettings)
    }

    inline fun <reified R> connect(collectionName: String): MongoCollection<R> =
        mongoClient.getDatabase("english").getCollection(collectionName, R::class.java).withCodecRegistry(codecRegistry)

    fun close() = mongoClient.close()
}