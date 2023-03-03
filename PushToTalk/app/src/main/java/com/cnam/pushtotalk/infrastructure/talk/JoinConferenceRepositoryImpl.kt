package com.cnam.pushtotalk.infrastructure.talk

import com.ale.infra.http.adapter.concurrent.RainbowServiceException
import com.ale.infra.manager.conference.IConferenceProxy
import com.ale.infra.manager.room.Room
import com.ale.infra.rest.conference.ConferenceRepository
import com.ale.infra.rest.listeners.RainbowError
import com.ale.infra.rest.listeners.RainbowListener
import com.ale.rainbowsdk.RainbowSdk
import com.cnam.pushtotalk.domain.talk.JoinConferenceRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class JoinConferenceRepositoryImpl : JoinConferenceRepository {
    override suspend fun joinConference(roomId: String): Result<Room> = suspendCoroutine { cont ->
        val room = RainbowSdk.instance().bubbles().findBubbleById(roomId)
        val callback = object :  RainbowListener<String, ConferenceRepository.StartJoinConferenceError> {
                override fun onSuccess(data: String) {
                if (room!!.conference != null) {
                    RainbowSdk.instance().webRTC().makeConferenceCall(roomId, data);
                    cont.resume(Result.success(room))
                }
            }

            override fun onError(error: RainbowError<ConferenceRepository.StartJoinConferenceError>) {
                super.onError(error)
                cont.resume(Result.failure(Exception(error.errorMsg)))
            }
        }

        if (room != null) {
            RainbowSdk.instance().bubbles().joinAudioConference(room, "", callback)
        }
        else {
            cont.resume(Result.failure(Exception("Room not found")))
        }
    }
}