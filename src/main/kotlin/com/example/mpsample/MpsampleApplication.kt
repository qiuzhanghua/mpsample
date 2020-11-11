package com.example.mpsample

import com.example.mpsample.domain.Person
import com.example.mpsample.domain.Source
import com.example.mpsample.mapper.SourceTargetMapper
import com.example.mpsample.dto.Target
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.time.LocalDate

@SpringBootApplication
class MpsampleApplication

fun main(args: Array<String>) {
    runApplication<MpsampleApplication>(*args)
}

@Component
class ApplicationStartupRunner : CommandLineRunner {

    @Autowired
    lateinit var mapper: SourceTargetMapper // DI usage without INSTANCE

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        ///////////////////////
        // Under kotlin, you'd better not use lombok
        // toString act bad
        val source = Source()
        source.foo = 42
        source.bar = 23L
        source.zip = 73
        println(source)
        val target: Target? = SourceTargetMapper.INSTANCE.sourceToTarget(source)
        println(target)
        ///////////////////////

        val person = Person("Samuel", "Jackson", "0123 334466", LocalDate.of(1948, 12, 21))
        println(person)
        println(mapper)
    }
}

