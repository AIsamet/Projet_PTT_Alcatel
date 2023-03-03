package com.cnam.pushtotalk.domain.talk

interface IStartToTalkUseCase {
    suspend fun execute(roomId: String): Result<Boolean>
}