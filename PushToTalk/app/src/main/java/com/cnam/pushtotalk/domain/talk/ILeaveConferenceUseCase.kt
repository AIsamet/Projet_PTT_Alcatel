package com.cnam.pushtotalk.domain.talk

import com.ale.infra.manager.room.Room

interface ILeaveConferenceUseCase {
    suspend fun execute(room: Room): Result<Unit>
}