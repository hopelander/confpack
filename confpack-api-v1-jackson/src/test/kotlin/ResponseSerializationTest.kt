package ru.kvartasoft.comfpack.api.v1

import ru.kvartasoft.api.v1.models.IResponse
import ru.kvartasoft.api.v1.models.ParticipantCreateResponse
import ru.kvartasoft.api.v1.models.ParticipantResponseObject
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {

    private val response = ParticipantCreateResponse(
        requestId = "123",
        participant = ParticipantResponseObject(
            lastName = "Ivanov"
        )
    )

    @Test
    fun serializate() {
        val json = apiV1Mapper.writeValueAsString(response);
        assertContains(json, Regex("\"requestId\":\\s*\"123\""))
        assertContains(json, Regex("\"lastName\":\\s*\"Ivanov\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserializate() {
        val json = apiV1Mapper.writeValueAsString(response);
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as ParticipantCreateResponse
        assertEquals(response, obj)
    }


}