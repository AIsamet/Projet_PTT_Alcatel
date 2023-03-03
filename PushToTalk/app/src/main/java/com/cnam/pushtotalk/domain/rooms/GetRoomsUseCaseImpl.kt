package com.cnam.pushtotalk.domain.rooms

class GetRoomsUseCaseImpl(private val getRoomsRepository: GetRoomsRepository) : IGetRoomsUseCase {
    override suspend fun execute(): Result<List<Room>> = getRoomsRepository.getRoomsForUser()
}