package com.example.mpsample.mapper

import com.example.mpsample.domain.Source
import com.example.mpsample.dto.Target
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers


@Mapper(componentModel = "spring")
interface SourceTargetMapper {
    @Mappings(Mapping(source = "qax", target = "baz"),
            Mapping(source = "baz", target = "qax")
    )
    fun sourceToTarget(source: Source?): Target?

    @InheritInverseConfiguration
    fun targetToSource(target: Target?): Source?

    companion object {
        val INSTANCE = Mappers.getMapper(SourceTargetMapper::class.java)
    }
}