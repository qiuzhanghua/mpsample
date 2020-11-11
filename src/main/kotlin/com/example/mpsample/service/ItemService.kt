package com.example.mpsample.service

import com.example.mpsample.domain.Item
import org.springframework.stereotype.Service

@Service
class ItemService {

    fun retriveOne(): Item {
        return Item(1, "iPhone", 10000, 2)
    }
}