package com.cnam.pushtotalk.domain.signin

interface SignInToRainbowWithTokenRepository {
    suspend fun signInToRainbow(token: String): Result<Unit>
}