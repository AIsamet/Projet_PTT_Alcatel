package com.cnam.pushtotalk.domain.talk

import com.ale.infra.manager.room.Room

class JoinConferenceUseCaseImpl(
    private val startConferenceRepository: StartConferenceRepository,
    private val joinConferenceRepository: JoinConferenceRepository,
    private val muteMicrophoneRepository: MuteMicrophoneRepository
) : IJoinConferenceUseCase {
    override suspend fun execute(roomId: String): Result<Room> =
        startConferenceRepository.startConference(roomId).mapCatching {
            joinConferenceRepository.joinConference(roomId).getOrThrow().also {
                muteMicrophoneRepository.muteMicrophone()
            }
        }
}