package com.cnam.pushtotalk.domain.talk

interface MuteMicrophoneRepository {
    fun muteMicrophone()
    fun unmuteMicrophone()
}