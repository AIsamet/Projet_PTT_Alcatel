package com.cnam.pushtotalk.domain.talk

import com.ale.infra.manager.room.Room

interface JoinConferenceRepository {
    suspend fun joinConference(roomId: String): Result<Room>
}