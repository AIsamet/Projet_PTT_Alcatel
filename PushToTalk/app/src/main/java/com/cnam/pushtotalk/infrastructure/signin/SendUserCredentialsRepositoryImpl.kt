package com.cnam.pushtotalk.infrastructure.signin

import com.cnam.pushtotalk.domain.signin.SendUserCredentialsRepository
import com.cnam.pushtotalk.domain.signin.SignInRequest
import com.cnam.pushtotalk.domain.signin.SignInResponse
import com.cnam.pushtotalk.infrastructure.util.Api
import com.cnam.pushtotalk.infrastructure.util.await
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class SendUserCredentialsRepositoryImpl : SendUserCredentialsRepository {
    private val moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    private val client by lazy { OkHttpClient() }

    override suspend fun getToken(request: SignInRequest): Result<SignInResponse> {
        val json = request.serialize()
        val body = json.toRequestBody("application/json".toMediaTypeOrNull())

        val httpRequest = Request.Builder()
            .url(Api.BASE_URL + SIGN_IN_ROUTE)
            .post(body)
            .build()

        val response = client.newCall(httpRequest).await()
        return try {
            Result.success(response.parse())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun SignInRequest.serialize(): String {
        val jsonAdapter = moshi.adapter(SignInRequest::class.java)
        return jsonAdapter.toJson(this)
    }

    private fun Response.parse(): SignInResponse {
        val jsonAdapter = moshi.adapter(SignInResponse::class.java)
        return jsonAdapter.fromJson(body!!.source())!!;
    }

    companion object {
        private const val SIGN_IN_ROUTE = "login"
    }
}