package com.cnam.pushtotalk.infrastructure.talk

import com.ale.rainbowsdk.RainbowSdk
import com.cnam.pushtotalk.domain.talk.MuteMicrophoneRepository

class MuteMicrophoneRepositoryImpl : MuteMicrophoneRepository {
    override fun muteMicrophone() {
        RainbowSdk.instance().webRTC().mute(mute = true, distant = false);
    }

    override fun unmuteMicrophone() {
        RainbowSdk.instance().webRTC().mute(mute = false, distant = false);
    }
}