package com.example.mpsample.service

import com.example.mpsample.domain.Item
import com.example.mpsample.domain.ItemRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ItemServiceTests {

    @InjectMocks
    lateinit var itemService: ItemService

    @Mock
    lateinit var itemRepository: ItemRepository

    @Test
    fun testRetrieveAll() {
        Mockito.`when`(itemRepository.findAll()).thenReturn(listOf(
                Item(1, "iPhone", 10000, 2),
                Item(2, "Watch", 4000, 1)
        ))
        val items = itemService.retrieveAll();
        Assertions.assertEquals(10000, items[0].price)
        Assertions.assertEquals(4000, items[1].price)

    }


}