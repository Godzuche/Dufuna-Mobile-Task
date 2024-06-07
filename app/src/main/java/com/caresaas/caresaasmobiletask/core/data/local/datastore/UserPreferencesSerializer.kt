package com.caresaas.caresaasmobiletask.core.data.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.caresaas.caresaasmobiletask.core.data.local.datastore.model.UserPreferences
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserPreferencesSerializer @Inject constructor(
//    private val cryptoManager: CryptoManager,
) : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences()

    override suspend fun readFrom(input: InputStream): UserPreferences {
//        val decryptedBytes = cryptoManager.decrypt(input)

        return try {
            Json.decodeFromString(
                deserializer = UserPreferences.serializer(),
//                string = decryptedBytes.decodeToString(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            /*e.printStackTrace()
            defaultValue*/
            throw CorruptionException(
                message = "Unable to read UserPreferences",
                e
            )
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
//        cryptoManager.encrypt(
//            bytes = Json.encodeToString(
//                serializer = UserPreferences.serializer(),
//                value = t
//            ).encodeToByteArray(),
//            outputStream = output
//        )
        output.write(
            Json.encodeToString(
                serializer = UserPreferences.serializer(),
                value = t
            ).encodeToByteArray(),
        )
    }
}