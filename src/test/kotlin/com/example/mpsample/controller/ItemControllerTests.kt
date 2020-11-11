package com.example.mpsample.controller

import com.example.mpsample.domain.Item
import com.example.mpsample.service.ItemService
import com.example.mpsample.web.ItemController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(ItemController::class)
class ItemControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var itemService: ItemService

    @Test
    fun testItemControllerOne() {
        Mockito.`when`(itemService.retrieveOne()).thenReturn(Item(2, "Watch", 4000, 1))

        val request = MockMvcRequestBuilders.get("/item").accept(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request)
                .andExpect(status().isOk)
                .andReturn()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.response.status, HttpStatus.OK.value())
        val expected = """
                        |{id:2, "name":"Watch", price:4000, "count":1}
                        |""".trimMargin()
        JSONAssert.assertEquals(expected, result.response.contentAsString, true)
    }


    @Test
    fun testItemControllerAll() {
        Mockito.`when`(itemService.retrieveAll()).thenReturn(listOf(
                Item(1, "iPhone", 10000, 2),
                Item(2, "Watch", 4000, 1)
        ))

        val request = MockMvcRequestBuilders.get("/items").accept(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request)
                .andExpect(status().isOk)
                .andReturn()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.response.status, HttpStatus.OK.value())
        val expected = """
                        |[{id:1, name:"iPhone", price:10000, count:2}, 
                        |{id:2, "name":"Watch", price:4000, "count":1}]
                        |""".trimMargin()
        JSONAssert.assertEquals(expected, result.response.contentAsString, true)
    }

}