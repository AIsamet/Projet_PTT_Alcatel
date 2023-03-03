package com.cnam.pushtotalk.infrastructure.talk

import com.ale.infra.manager.conference.IConferenceProxy
import com.ale.infra.manager.room.Room
import com.ale.infra.rest.conference.ConferenceRepository
import com.ale.infra.rest.listeners.RainbowError
import com.ale.infra.rest.listeners.RainbowListener
import com.ale.rainbowsdk.RainbowSdk
import com.cnam.pushtotalk.domain.talk.LeaveConferenceRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LeaveConferenceRepositoryImpl : LeaveConferenceRepository {
    override suspend fun leaveConference(room: Room): Result<Unit> = suspendCoroutine { cont ->
        RainbowSdk.instance().bubbles().hangUpParticipant(
            room,
            room.conference?.getMeAsParticipant(),
            object : RainbowListener<Unit, ConferenceRepository.ConferenceOptionsError> {
                override fun onSuccess(data: Unit) {
                    cont.resume(Result.success(Unit))
                }

                override fun onError(error: RainbowError<ConferenceRepository.ConferenceOptionsError>) {
                    cont.resume(Result.failure(Exception(error.errorMsg)))
                }
            })
    }
}