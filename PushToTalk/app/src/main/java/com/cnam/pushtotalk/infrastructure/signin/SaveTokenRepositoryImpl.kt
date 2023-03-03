package com.cnam.pushtotalk.infrastructure.signin

import com.cnam.pushtotalk.domain.signin.SaveTokenRepository
import com.cnam.pushtotalk.infrastructure.util.Api

class SaveTokenRepositoryImpl : SaveTokenRepository {
    override fun saveToken(token: String) {
        Api.instance.token = token
    }
}
