package com.example.mpsample.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Item(@Id var id: Int,
                var name: String,
                var price: Int,
                var count: Int)
