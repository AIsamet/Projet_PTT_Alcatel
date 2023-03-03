package com.cnam.pushtotalk.presentation.rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cnam.pushtotalk.domain.rooms.Room
import com.cnam.pushtotalk.domain.rooms.IGetRoomsUseCase
import com.cnam.pushtotalk.presentation.UseCaseFactory
import kotlinx.coroutines.launch

class RoomsViewModel: ViewModel() {
    private val getRoomsUseCase: IGetRoomsUseCase = UseCaseFactory.getRoomsUseCase

    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: MutableLiveData<List<Room>> get() = _rooms

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun getRooms() {
        viewModelScope.launch {
            getRoomsUseCase.execute().onSuccess {
                _rooms.postValue(it)
            }.onFailure {
                _error.postValue(it.message)
            }
        }
    }
}