package com.agenda.aplicativodefilmes.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agenda.aplicativodefilmes.repository.MoviesRepository
import com.agenda.aplicativodefilmes.viewmodel.MoviesViewModel

class MoviesViewModelFactory(private val moviesRepository: MoviesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesRepository) as T
    }
}