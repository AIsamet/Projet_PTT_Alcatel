package com.cnam.pushtotalk.domain.talk

import com.ale.infra.manager.room.Room

interface LeaveConferenceRepository {
    suspend fun leaveConference(room: Room): Result<Unit>
}