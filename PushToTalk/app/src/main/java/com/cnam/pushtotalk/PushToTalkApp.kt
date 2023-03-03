package com.cnam.pushtotalk

import android.app.Application
import com.ale.rainbowsdk.RainbowSdk

class PushToTalkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RainbowSdk.instance().initialize(this,
            BuildConfig.RAINBOW_APP_ID,
            BuildConfig.RAINBOW_APP_SECRET
        )
    }
}
