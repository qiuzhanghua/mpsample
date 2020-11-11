package com.example.mpsample

import com.example.mpsample.domain.Person
import com.example.mpsample.domain.Source
import com.example.mpsample.dto.PersonDto
import com.example.mpsample.mapper.PersonConverter
import com.example.mpsample.dto.Target
import com.example.mpsample.mapper.SourceTargetMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate


@SpringBootTest
class MpsampleApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun testMapStruct() {
        val source = Source()
        source.foo = 42
        source.bar = 23L
        source.zip = 73
        val target: Target? = SourceTargetMapper.INSTANCE.sourceToTarget(source)
        Assertions.assertNotNull(target)
        Assertions.assertEquals(target!!.foo, 42L)
        Assertions.assertEquals(target.bar, 23)
        Assertions.assertEquals(target.zip, "73")
    }


    @Test
    fun testConvertToDto() {
        val converter = Mappers.getMapper(PersonConverter::class.java)

        val person = Person("Samuel", "Jackson", "0123 334466", LocalDate.of(1948, 12, 21))

        val personDto = converter.convertToDto(person)
        Assertions.assertNotNull(personDto)
        Assertions.assertEquals(personDto.firstName, "Samuel")
        Assertions.assertEquals(personDto.lastName, "Jackson")
        Assertions.assertEquals(personDto.phone, "0123 334466")
        Assertions.assertEquals(personDto.birthdate, LocalDate.of(1948, 12, 21))
    }

    @Test
    fun testConvertToModel() {
        val converter = Mappers.getMapper(PersonConverter::class.java)

        val personDto = PersonDto("Samuel", "Jackson", "0123 334466", LocalDate.of(1948, 12, 21))

        val person = converter.convertToModel(personDto)
        Assertions.assertNotNull(person)
        Assertions.assertEquals(person.firstName, "Samuel")
        Assertions.assertEquals(person.lastName, "Jackson")
        Assertions.assertEquals(person.phoneNumber, "0123 334466")
        Assertions.assertEquals(person.birthdate, LocalDate.of(1948, 12, 21))
    }

}
