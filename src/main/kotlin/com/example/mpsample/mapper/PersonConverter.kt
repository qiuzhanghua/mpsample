package com.example.mpsample.mapper

import com.example.mpsample.domain.Person
import com.example.mpsample.dto.PersonDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface PersonConverter {

    @Mapping(source = "phoneNumber", target = "phone")
    fun convertToDto(person: Person): PersonDto

    @InheritInverseConfiguration
    fun convertToModel(personDto: PersonDto): Person

}
