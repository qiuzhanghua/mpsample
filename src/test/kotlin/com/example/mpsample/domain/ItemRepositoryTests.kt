package com.example.mpsample.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

// @ExtendWith(MockitoExtension::class)
@DataJpaTest
class ItemRepositoryTests {

    @Autowired
    lateinit var itemRepository: ItemRepository

    @Test
    fun testFindAll() {
        val items = itemRepository.findAll();
        Assertions.assertEquals(3, items.size)
    }

}