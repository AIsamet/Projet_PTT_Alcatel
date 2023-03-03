package com.cnam.pushtotalk.domain.signin

import com.squareup.moshi.JsonClass

interface ISignInToRainbowUseCase {
    suspend fun execute(request: SignInRequest): Result<Unit>
}

@JsonClass(generateAdapter = true)
data class SignInRequest(val email: String, val password: String)

@JsonClass(generateAdapter = true)
data class SignInResponse(val token: String)