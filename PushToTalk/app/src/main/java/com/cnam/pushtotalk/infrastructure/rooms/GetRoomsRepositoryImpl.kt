package com.cnam.pushtotalk.infrastructure.rooms

import com.cnam.pushtotalk.domain.rooms.GetRoomsRepository
import com.cnam.pushtotalk.domain.rooms.Room
import com.cnam.pushtotalk.infrastructure.util.Api
import com.cnam.pushtotalk.infrastructure.util.await
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.lang.reflect.Type


class GetRoomsRepositoryImpl : GetRoomsRepository {
    private val moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    private val client by lazy { OkHttpClient() }

    override suspend fun getRoomsForUser(): Result<List<Room>>
    {
        val httpRequest = Request.Builder()
            .url(Api.BASE_URL+ GET_ROOMS_ROUTE)
            .addHeader("Authorization","Bearer ${Api.instance.token}")
            .build()

        val response = client.newCall(httpRequest).await()
        return try {
            Result.success(response.parse())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun Response.parse(): List<Room>{
        val listRoomsResponse: Type = Types.newParameterizedType(
            List::class.java,
            Room::class.java
        )
        val jsonAdapter: JsonAdapter<List<Room>> = moshi.adapter(listRoomsResponse)
        return jsonAdapter.fromJson(this.body!!.source())!!
    }

    companion object {
        private const val GET_ROOMS_ROUTE = "rooms"
    }

}