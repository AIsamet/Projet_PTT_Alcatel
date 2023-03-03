package com.cnam.pushtotalk.infrastructure.signin

import com.ale.listener.SigninResponseListener
import com.ale.rainbowsdk.RainbowSdk
import com.cnam.pushtotalk.domain.signin.SignInToRainbowWithTokenRepository
import com.cnam.pushtotalk.infrastructure.util.Api
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SignInToRainbowRepositoryImpl : SignInToRainbowWithTokenRepository {
    override suspend fun signInToRainbow(token: String): Result<Unit> =
        suspendCoroutine { cont ->
            val callback = object : SigninResponseListener() {
                override fun onRequestFailed(errorCode: RainbowSdk.ErrorCode?, err: String?) {
                    cont.resume(Result.failure(Exception(err)))
                }

                override fun onSigninSucceeded() {
                    cont.resume(Result.success(Unit))
                }
            }
            RainbowSdk.instance().connection().signinWithToken(token, Api.RAINBOW_HOST, callback)
        }
}