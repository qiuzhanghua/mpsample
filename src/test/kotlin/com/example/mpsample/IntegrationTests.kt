package com.example.mpsample

import com.example.mpsample.domain.Item
import com.example.mpsample.domain.ItemRepository
import com.jayway.jsonpath.JsonPath
import org.json.JSONException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    // if I don't want to visit db, then I will use MockBean
//    @MockBean
//    lateinit var itemRepository: ItemRepository

    @Test
    @Throws(JSONException::class)
    fun testItemsIntegration() {
//        Mockito.`when`(itemRepository.findAll()).thenReturn(listOf(Item(1, "iPhone", 10000, 3)))
        val response = testRestTemplate.getForObject("/items", String::class.java)
//        JSONAssert.assertEquals("""[{id:1, name:"iPhone", price:10000, count:3}]""", response, true)
        JSONAssert.assertEquals("""[
                                    |{id:1001, name:"Item 1", price:100, count:1},
                                    |{id:1002, name:"Item 2", price:200, count:12},
                                    |{id:1003, name:"Item 3", price:300, count:123}
                                    |]""".trimMargin(), response, true)
        // JSONPath usage
        val context = JsonPath.parse(response)
        val len = context.read<Int>("$.length()")
        Assertions.assertEquals(3, len)
        val ids = context.read<List<Int>>("$..id")
        Assertions.assertEquals(listOf(1001, 1002, 1003), ids)
        println(context.read<String>("$.[1]"))
        println(context.read<String>("$.[0:1]"))
        println(context.read<String>("$.[?(@.name=='Item 3')]"))
    }

}
