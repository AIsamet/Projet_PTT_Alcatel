package com.cnam.pushtotalk.domain.talk

class StopToTalkUseCaseImpl(
    private val conferenceSpeakerRepository: ConferenceSpeakerRepository,
    private val muteMicrophoneRepository: MuteMicrophoneRepository
) : IStopToTalkUseCase {
    override suspend fun execute(roomId: String): Result<Unit> =
        conferenceSpeakerRepository.toggleConferenceSpeaker(roomId).mapCatching {
            muteMicrophoneRepository.muteMicrophone()
        }
}