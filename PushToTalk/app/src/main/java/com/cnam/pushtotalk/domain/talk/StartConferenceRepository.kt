package com.cnam.pushtotalk.domain.talk

interface StartConferenceRepository {
    suspend fun startConference(roomId: String): Result<Conference>
}