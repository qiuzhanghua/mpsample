package com.example.mpsample.web

import com.example.mpsample.domain.Item
import com.example.mpsample.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController {

    @Autowired
    lateinit var itemService: ItemService

    @GetMapping("/item")
    fun hello() : Item {
        return itemService.retriveOne()
        // return Item(1, "iPhone", 10000, 2)
    }
}