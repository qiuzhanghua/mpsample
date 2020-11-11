package com.example.mpsample.service

import com.example.mpsample.domain.Item
import com.example.mpsample.domain.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ItemService(val itemRepository: ItemRepository) {

//    @Autowired
//    lateinit var itemRepository: ItemRepository

    fun retrieveOne(): Item {
        return Item(1, "iPhone", 10000, 2)
    }

    fun retrieveAll() : List<Item> {
        return itemRepository.findAll()
    }
}