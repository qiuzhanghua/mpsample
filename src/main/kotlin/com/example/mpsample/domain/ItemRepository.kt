package com.example.mpsample.domain

import org.springframework.data.jpa.repository.JpaRepository


interface ItemRepository : JpaRepository<Item, Int>