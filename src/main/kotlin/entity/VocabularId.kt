package entity

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.types.ObjectId
import java.util.*

@Serializable(with = VocabularIdSerializer::class)
data class VocabularId(
    var id: String = UUID.randomUUID().toString()
) {
    constructor(id: ObjectId) : this(id.toHexString())

    fun toObjectId() = ObjectId(this.id)

    override fun toString() = this.id
}

class VocabularIdCodec : Codec<VocabularId> {
    override fun encode(writer: BsonWriter, value: VocabularId, encoderContext: EncoderContext) =
        writer.writeObjectId(
            ObjectId(value.id)
        )

    override fun getEncoderClass() = VocabularId::class.java

    override fun decode(reader: BsonReader, decoderContext: DecoderContext) =
        VocabularId(reader.readObjectId())

}

class VocabularIdSerializer : KSerializer<VocabularId> {
    override fun deserialize(decoder: Decoder) = VocabularId(decoder.decodeString())

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("VocabularId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: VocabularId) {
        encoder.encodeString(value.toString())
    }

}