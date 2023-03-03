package com.cnam.pushtotalk.domain.talk

interface ConferenceSpeakerRepository {
    suspend fun isSomeoneAlreadyTalking(roomId: String): Result<Boolean>
    suspend fun toggleConferenceSpeaker(roomId: String): Result<Unit>
}