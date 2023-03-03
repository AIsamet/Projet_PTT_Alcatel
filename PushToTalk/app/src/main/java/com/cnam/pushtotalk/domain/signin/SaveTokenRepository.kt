package com.cnam.pushtotalk.domain.signin

interface SaveTokenRepository {
    fun saveToken(token: String)
}