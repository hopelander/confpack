package ru.kvartasoft.comfpack.api.v1

import ru.kvartasoft.api.v1.models.*
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {

    private val request = ParticipantCreateRequest(
        requestId = "123",
        debug = ParticipantDebug(
            mode = RequestDebugMode.STUB,
            stub = ParticipantRequestDebugStubs.BAD_EMAIL
        ),
          participant = ParticipantCreateObject(
              lastName = "Ivanov",
              firstName = "Ivan",
              secondName = "Ivanovich",
              email = "ivanov@email.com",
              phone = "+79000000000"
          )
    )

    @Test
    fun serialization() {
        val json = apiV1Mapper.writeValueAsString(request);
        assertContains(json, Regex("\"requestId\":\\s*\"123\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badEmail\""))
        assertContains(json, Regex("\"lastName\":\\s*\"Ivanov\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as ParticipantCreateRequest
        assertEquals(request, obj)
    }

}