package com.agenda.aplicativodefilmes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.aplicativodefilmes.repository.UserRepository
import com.agenda.aplicativodefilmes.services.LoginListener

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _currentUser = MutableLiveData<Boolean>()
    val currentUser: LiveData<Boolean> = _currentUser

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    fun userSign(email: String, password: String){
        userRepository.userSignRepository(email,password, object : LoginListener{
            override fun onSucess() {
                _login.postValue(true)
                _message.postValue("Sucesso ao realizar o login")
            }

            override fun onError(text: String) {
                _login.postValue(false)
            }

        })
    }
    fun currentUser(){
        userRepository.currentUserRepository(object : LoginListener{
            override fun onSucess() {
                _currentUser.postValue(true)
            }

            override fun onError(text: String) {
                _currentUser.postValue(false)
            }
        })
    }
}