package com.cnam.pushtotalk.domain.talk

import com.ale.infra.manager.room.Room

class LeaveConferenceUseCaseImpl(
    private val leaveConferenceRepository: LeaveConferenceRepository
) : ILeaveConferenceUseCase {
    override suspend fun execute(room: Room): Result<Unit> =
        leaveConferenceRepository.leaveConference(room)
}