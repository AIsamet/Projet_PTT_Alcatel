package com.cnam.pushtotalk.domain.signin

class SignInToRainbowUseCaseImpl(
    private val sendUserCredentialsRepository: SendUserCredentialsRepository,
    private val rainbowRepository: SignInToRainbowWithTokenRepository,
    private val saveTokenRepository: SaveTokenRepository
) : ISignInToRainbowUseCase {
    override suspend fun execute(request: SignInRequest): Result<Unit> {
        return sendUserCredentialsRepository.getToken(request).mapCatching {
            rainbowRepository.signInToRainbow(it.token)
            saveTokenRepository.saveToken(it.token)
        }
    }
}