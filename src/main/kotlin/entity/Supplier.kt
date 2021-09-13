package entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
class Supplier() {
    @BsonId
    @Contextual
    var objectId: VocabularId? = null

    lateinit var name: String

    var description: String? = null

    constructor(name: String, description: String? = null) : this() {
        this.name = name
        this.description = description
    }
}