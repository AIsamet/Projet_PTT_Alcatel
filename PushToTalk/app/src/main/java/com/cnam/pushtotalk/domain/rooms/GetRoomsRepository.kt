package com.cnam.pushtotalk.domain.rooms

interface GetRoomsRepository {
    suspend fun getRoomsForUser(): Result<List<Room>>
}