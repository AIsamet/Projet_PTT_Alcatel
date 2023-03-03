package com.cnam.pushtotalk.presentation.talk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ale.infra.manager.room.Room
import com.cnam.pushtotalk.domain.talk.IJoinConferenceUseCase
import com.cnam.pushtotalk.domain.talk.ILeaveConferenceUseCase
import com.cnam.pushtotalk.domain.talk.IStartToTalkUseCase
import com.cnam.pushtotalk.domain.talk.IStopToTalkUseCase
import com.cnam.pushtotalk.presentation.UseCaseFactory
import kotlinx.coroutines.launch

class TalkViewModel : ViewModel() {
    private val joinConferenceUseCase: IJoinConferenceUseCase = UseCaseFactory.joinConferenceUseCase
    private val leaveConferenceUseCase: ILeaveConferenceUseCase = UseCaseFactory.leaveConferenceUseCase
    private val startToTalkUseCaseImpl: IStartToTalkUseCase = UseCaseFactory.startToTalkUseCase
    private val stopToTalkUseCaseImpl: IStopToTalkUseCase = UseCaseFactory.stopToTalkUseCase

    private val _conferenceRoom = MutableLiveData<Room>()

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    private val _conferenceJoined = MutableLiveData<Boolean>()
    val conferenceJoined: MutableLiveData<Boolean> get() = _conferenceJoined

    private val _isTalking = MutableLiveData<Boolean>()
    val isTalking: MutableLiveData<Boolean> get() = _isTalking

    fun joinConference(roomId: String) {
        viewModelScope.launch {
            joinConferenceUseCase.execute(roomId)
                .onSuccess {
                    _conferenceRoom.postValue(it)
                    _conferenceJoined.postValue(true)
                }
                .onFailure { _error.postValue(it.message) }
        }
    }

    fun leaveConference() {
        viewModelScope.launch {
            _conferenceRoom.value?.let { room ->
                leaveConferenceUseCase.execute(room)
                    .onSuccess { _conferenceJoined.postValue(false) }
                    .onFailure { _error.postValue(it.message) }
            }
        }
    }

    fun startToTalk(roomId: String) {
        if (_conferenceJoined.value == true) {
            viewModelScope.launch {
                startToTalkUseCaseImpl.execute(roomId)
                    .onSuccess { _isTalking.postValue(it) }
                    .onFailure { _error.postValue(it.message) }
            }
        }
    }

    fun stopToTalk(roomId: String) {
        if (_conferenceJoined.value == true && _isTalking.value == true) {
            viewModelScope.launch {
                stopToTalkUseCaseImpl.execute(roomId)
                    .onSuccess { _isTalking.postValue(false) }
                    .onFailure { _error.postValue(it.message) }
            }
        }
    }
}