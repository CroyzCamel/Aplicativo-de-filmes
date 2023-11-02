package com.agenda.aplicativodefilmes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.aplicativodefilmes.repository.UserRepository
import com.agenda.aplicativodefilmes.services.RegisterListener

class RegisterViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _register = MutableLiveData<Boolean>()
    val register: LiveData<Boolean> = _register

    private val _message = MutableLiveData<String>()
    val message: MutableLiveData<String> = _message

    fun registerUser(email:String, password: String){
        userRepository.database.userRegister(email,password, object : RegisterListener {
            override fun onSucess() {
                _register.postValue(true)
                _message.postValue("Sucesso ao cadastrar o usuário!!!")
            }

            override fun onError(text: String) {
                _register.postValue(false)
                _message.postValue("Erro ao cadastrar o usuário!!!")
            }

        })
    }
}