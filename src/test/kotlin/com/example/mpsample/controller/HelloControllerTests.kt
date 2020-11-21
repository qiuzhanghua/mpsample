package com.example.mpsample.controller

import com.example.mpsample.web.HelloController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.returnResult

@WebFluxTest(HelloController::class)
class HelloControllerTests {

    @Autowired
    lateinit var client: WebTestClient

    @Test
    fun testHelloController() {

        val response = client.get().uri("/hello").accept(MediaType.APPLICATION_JSON).exchange()
        response.expectStatus().isOk
        println(response.expectBody<String>())
        Assertions.assertEquals("Hello", response.returnResult<String>().responseBody.blockLast())
    }
}