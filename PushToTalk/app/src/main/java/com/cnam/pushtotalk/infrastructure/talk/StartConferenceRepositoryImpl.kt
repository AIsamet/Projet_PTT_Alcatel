package com.cnam.pushtotalk.infrastructure.talk

import com.cnam.pushtotalk.domain.talk.Conference
import com.cnam.pushtotalk.domain.talk.StartConferenceRepository
import com.cnam.pushtotalk.infrastructure.util.Api
import com.cnam.pushtotalk.infrastructure.util.await
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class StartConferenceRepositoryImpl : StartConferenceRepository {
    private val moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    private val client by lazy { OkHttpClient() }

    override suspend fun startConference(roomId: String): Result<Conference> {
        val httpRequest = Request.Builder()
            .url(Api.BASE_URL + getConferenceUrl(roomId))
            .build()

        val response = client.newCall(httpRequest).await()
        return try {
            val conference = response.parse()
            if (conference.conferenceId != "null"){
                Result.success(conference)
            } else {
                Result.failure(Exception("Conference not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getConferenceUrl(roomId: String): String = "rooms/$roomId/conference"

    private fun Response.parse(): Conference {
        val jsonAdapter = moshi.adapter(Conference::class.java)
        return jsonAdapter.fromJson(body!!.source())!!
    }
}