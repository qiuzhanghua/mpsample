package com.example.mpsample.controller

import com.example.mpsample.web.HelloController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(HelloController::class)
class HelloControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testHelloController() {
        // two method to perform test
        val request = MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().string("Hello"))
                .andReturn()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.response.status, HttpStatus.OK.value())
        Assertions.assertEquals("Hello", result.response.contentAsString)
    }
}