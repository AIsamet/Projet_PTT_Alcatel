package com.cnam.pushtotalk.domain.talk

class StartToTalkUseCaseImpl(
    private val conferenceSpeakerRepository: ConferenceSpeakerRepository,
    private val muteMicrophoneRepository: MuteMicrophoneRepository
) : IStartToTalkUseCase {
    override suspend fun execute(roomId: String): Result<Boolean> =
        conferenceSpeakerRepository.isSomeoneAlreadyTalking(roomId).mapCatching {
            if (!it) {
                conferenceSpeakerRepository.toggleConferenceSpeaker(roomId)
                muteMicrophoneRepository.unmuteMicrophone()
                true
            }
            else false
        }
}