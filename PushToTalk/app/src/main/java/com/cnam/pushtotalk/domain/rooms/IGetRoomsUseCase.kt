package com.cnam.pushtotalk.domain.rooms

import com.squareup.moshi.JsonClass

interface IGetRoomsUseCase {
    suspend fun execute() : Result<List<Room>>
}

@JsonClass(generateAdapter = true)
data class Room(val id: String, val name: String)