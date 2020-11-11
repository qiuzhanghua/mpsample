package com.example.mpsample.web

import com.example.mpsample.domain.Item
import com.example.mpsample.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(val itemService: ItemService) {

//    @Autowired
//    lateinit var itemService: ItemService

    @GetMapping("/item")
    fun item() : Item {
        return itemService.retrieveOne()
        // return Item(1, "iPhone", 10000, 2)
    }

    @GetMapping("/items")
    fun items() : List<Item> {
        return itemService.retrieveAll()
    }
}