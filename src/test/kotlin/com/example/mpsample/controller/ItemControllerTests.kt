package com.example.mpsample.controller

import com.example.mpsample.domain.Item
import com.example.mpsample.service.ItemService
import com.example.mpsample.web.ItemController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest(ItemController::class)
class ItemControllerTests {

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var itemService: ItemService

    @Test
    fun testItemControllerOne() {
        val item = Item(2, "Watch", 4000, 1)
        Mockito.`when`(itemService.retrieveOne()).thenReturn(item)

        val response = client.get().uri("/item").accept(MediaType.APPLICATION_JSON).exchange()
        response.expectStatus().isOk

        Assertions.assertEquals(item, response.expectBody<Item>().returnResult().responseBody)
        // or
        val s = response.expectBody<String>().returnResult().responseBody
        val expected = """
                        |{id:2, "name":"Watch", price:4000, "count":1}
                        |""".trimMargin()
        JSONAssert.assertEquals(expected, s, true)
    }


    @Test
    fun testItemControllerAll() {
        val items = listOf(
                Item(1, "iPhone", 10000, 2),
                Item(2, "Watch", 4000, 1)
        )
        Mockito.`when`(itemService.retrieveAll()).thenReturn(items)

        val response = client.get().uri("/items").accept(MediaType.APPLICATION_JSON).exchange()
        response.expectStatus().isOk

        Assertions.assertEquals(items, response.expectBody<List<Item>>().returnResult().responseBody)
        // or
        val s = response.expectBody<String>().returnResult().responseBody
        val expected = """
                        |[{id:1, name:"iPhone", price:10000, count:2},
                        |{id:2, "name":"Watch", price:4000, "count":1}]
                        |""".trimMargin()
        JSONAssert.assertEquals(expected, s, true)
    }

}

// see
// https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/
// https://www.sudoinit5.com/post/spring-boot-testing-producer/
// https://grokonez.com/testing/springboot-webflux-test-webfluxtest
// https://blog.knoldus.com/spring-webflux-how-to-test-your-controllers-using-webtestclient/
// for more
//