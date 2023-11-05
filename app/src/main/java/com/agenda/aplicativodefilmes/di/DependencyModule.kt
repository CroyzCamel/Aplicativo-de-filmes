package com.agenda.aplicativodefilmes.di

import com.agenda.aplicativodefilmes.api.RetrofitServices
import com.agenda.aplicativodefilmes.repository.MoviesRepository
import com.agenda.aplicativodefilmes.repository.UserRepository
import com.agenda.aplicativodefilmes.viewmodel.LoginViewModel
import com.agenda.aplicativodefilmes.viewmodel.MoviesViewModel
import com.agenda.aplicativodefilmes.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyModule {


    val loginModule = module {
        factory { UserRepository() }
        viewModel { LoginViewModel(get()) }
    }
    val  registerModule = module {
        factory { UserRepository() }
        viewModel { RegisterViewModel(get()) }
    }
    val homeModule = module {
        single<RetrofitServices> { RetrofitServices.getInstance() }
        factory { MoviesRepository(get()) }
        viewModel { MoviesViewModel(get()) }

    }
}

