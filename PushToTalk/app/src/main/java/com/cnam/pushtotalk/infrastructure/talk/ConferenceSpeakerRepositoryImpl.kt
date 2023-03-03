package com.cnam.pushtotalk.infrastructure.talk

import com.cnam.pushtotalk.domain.talk.ConferenceSpeakerRepository
import com.cnam.pushtotalk.infrastructure.util.Api
import com.cnam.pushtotalk.infrastructure.util.await
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.EMPTY_REQUEST

class ConferenceSpeakerRepositoryImpl : ConferenceSpeakerRepository {
    private val client by lazy { OkHttpClient() }

    override suspend fun isSomeoneAlreadyTalking(roomId: String): Result<Boolean> {
        val httpRequest = Request.Builder()
            .url(Api.BASE_URL + "conference/speaker/$roomId")
            .build()

        val response = client.newCall(httpRequest).await()
        return try {
            val isSomeoneAlreadyTalking = response.body!!.string().toBoolean()
            Result.success(isSomeoneAlreadyTalking)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleConferenceSpeaker(roomId: String): Result<Unit> {
        val httpRequest = Request.Builder()
            .url(Api.BASE_URL + "conference/speaker/$roomId")
            .put(EMPTY_REQUEST)
            .build()

        val response = client.newCall(httpRequest).await()
        return when (response.isSuccessful) {
            true -> Result.success(Unit)
            false -> Result.failure(Exception(response.message))
        }
    }
}