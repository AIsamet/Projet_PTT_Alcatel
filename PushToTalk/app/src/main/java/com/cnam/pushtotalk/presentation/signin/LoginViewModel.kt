package com.cnam.pushtotalk.presentation.signin

import androidx.lifecycle.*
import com.cnam.pushtotalk.domain.signin.ISignInToRainbowUseCase
import com.cnam.pushtotalk.domain.signin.SignInRequest
import com.cnam.pushtotalk.presentation.UseCaseFactory
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val signInToRainbowUseCase: ISignInToRainbowUseCase =
        UseCaseFactory.signInToRainbowUseCase

    private val _signInStatus = MutableLiveData<Boolean>()
    val signInStatus: MutableLiveData<Boolean> get() = _signInStatus

    fun signInToRainbow(email: String, password: String) {
        val request = SignInRequest(email, password)
        viewModelScope.launch {
            val isSignedIn = signInToRainbowUseCase.execute(request).isSuccess
            _signInStatus.postValue(isSignedIn)
        }
    }
}