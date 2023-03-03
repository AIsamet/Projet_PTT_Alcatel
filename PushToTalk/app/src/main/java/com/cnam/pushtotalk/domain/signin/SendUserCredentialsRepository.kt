package com.cnam.pushtotalk.domain.signin

interface SendUserCredentialsRepository {
    suspend fun getToken(request: SignInRequest): Result<SignInResponse>
}