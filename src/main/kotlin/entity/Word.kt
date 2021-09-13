package entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
class Word() {
    @BsonId
    @Contextual
    var id: VocabularId? = null

    var en: String? = null

    var ru: String? = null

    var attempt: Int? = 0

    var supplierId: VocabularId? = null

    var supplier: Supplier? = null

    constructor(id: VocabularId? = null, en: String, ru: String, attempt: Int = 0, supplierId: VocabularId?) : this() {
        this.id = id
        this.en = en
        this.ru = ru
        this.attempt = attempt
        this.supplierId = supplierId
    }
}