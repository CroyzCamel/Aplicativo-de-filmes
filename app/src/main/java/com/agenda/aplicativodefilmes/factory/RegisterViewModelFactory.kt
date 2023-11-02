package com.agenda.aplicativodefilmes.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.aplicativodefilmes.repository.UserRepository
import com.agenda.aplicativodefilmes.viewmodel.RegisterViewModel

class RegisterViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(userRepository) as T
    }
}