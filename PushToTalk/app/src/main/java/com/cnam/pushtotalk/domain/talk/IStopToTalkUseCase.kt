package com.cnam.pushtotalk.domain.talk

interface IStopToTalkUseCase {
    suspend fun execute(roomId: String): Result<Unit>
}