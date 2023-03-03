package com.cnam.pushtotalk.presentation

import com.cnam.pushtotalk.domain.rooms.GetRoomsUseCaseImpl
import com.cnam.pushtotalk.domain.rooms.IGetRoomsUseCase
import com.cnam.pushtotalk.domain.signin.ISignInToRainbowUseCase
import com.cnam.pushtotalk.domain.signin.SignInToRainbowUseCaseImpl
import com.cnam.pushtotalk.domain.talk.*
import com.cnam.pushtotalk.infrastructure.rooms.GetRoomsRepositoryImpl
import com.cnam.pushtotalk.infrastructure.signin.SaveTokenRepositoryImpl
import com.cnam.pushtotalk.infrastructure.signin.SendUserCredentialsRepositoryImpl
import com.cnam.pushtotalk.infrastructure.signin.SignInToRainbowRepositoryImpl
import com.cnam.pushtotalk.infrastructure.talk.*

class UseCaseFactory {
    companion object {
        val signInToRainbowUseCase: ISignInToRainbowUseCase by lazy {
            SignInToRainbowUseCaseImpl(
                SendUserCredentialsRepositoryImpl(),
                SignInToRainbowRepositoryImpl(),
                SaveTokenRepositoryImpl()
            )
        }

        val getRoomsUseCase: IGetRoomsUseCase by lazy {
            GetRoomsUseCaseImpl(
                GetRoomsRepositoryImpl()
            )
        }

        val joinConferenceUseCase: IJoinConferenceUseCase by lazy {
            JoinConferenceUseCaseImpl(
                StartConferenceRepositoryImpl(),
                JoinConferenceRepositoryImpl(),
                MuteMicrophoneRepositoryImpl()
            )
        }

        val leaveConferenceUseCase: ILeaveConferenceUseCase by lazy {
            LeaveConferenceUseCaseImpl(
                LeaveConferenceRepositoryImpl()
            )
        }

        val startToTalkUseCase: IStartToTalkUseCase by lazy {
            StartToTalkUseCaseImpl(
                ConferenceSpeakerRepositoryImpl(),
                MuteMicrophoneRepositoryImpl()
            )
        }

        val stopToTalkUseCase: IStopToTalkUseCase by lazy {
            StopToTalkUseCaseImpl(
                ConferenceSpeakerRepositoryImpl(),
                MuteMicrophoneRepositoryImpl()
            )
        }
    }
}