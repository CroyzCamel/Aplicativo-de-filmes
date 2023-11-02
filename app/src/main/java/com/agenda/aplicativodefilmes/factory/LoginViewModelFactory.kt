package com.agenda.aplicativodefilmes.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.aplicativodefilmes.repository.UserRepository
import com.agenda.aplicativodefilmes.viewmodel.LoginViewModel

class LoginViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}