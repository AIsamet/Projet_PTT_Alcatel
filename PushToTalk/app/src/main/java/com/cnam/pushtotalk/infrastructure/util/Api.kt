package com.cnam.pushtotalk.infrastructure.util

class Api {
    var token: String = ""

    companion object {
        val instance = Api()
        const val BASE_URL = "https://deploy-rainbow-spring-production.up.railway.app/api/"
        const val RAINBOW_HOST = "sandbox.openrainbow.com"
    }
}