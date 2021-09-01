package entity

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

@Serializable
class Word() {
    @BsonProperty("_id")
    private var id: ObjectId? = null

    @BsonProperty("en")
    private var en: String? = null

    @BsonProperty("ru")
    private var ru: String? = null

    constructor(en: String, ru: String) : this() {
        this.en = en
        this.ru = ru
    }
}