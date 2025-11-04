package com.work.data

import com.work.data.lds.models.CharacterDomain
import com.work.data.lds.models.CharacterEntity
import com.work.data.network.models.MyCharacter

object CharacterMapper {

    fun toEntity(character: MyCharacter): CharacterEntity = CharacterEntity(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        type = character.type,
        gender = character.gender,
        image = character.image,
        originName = character.origin.name,
        locationName = character.location.name
    )

    fun toDomain(entity: CharacterEntity): CharacterDomain = CharacterDomain(
        id = entity.id,
        name = entity.name,
        status = entity.status,
        species = entity.species,
        type = entity.type,
        gender = entity.gender,
        image = entity.image,
        origin = entity.originName,
        location = entity.locationName
    )

}