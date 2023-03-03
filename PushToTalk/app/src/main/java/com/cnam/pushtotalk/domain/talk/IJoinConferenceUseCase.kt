package com.cnam.pushtotalk.domain.talk

import com.ale.infra.manager.room.Room
import com.squareup.moshi.JsonClass

interface IJoinConferenceUseCase {
    suspend fun execute(roomId: String): Result<Room>
}

@JsonClass(generateAdapter = true)
data class Conference(val conferenceId: String)