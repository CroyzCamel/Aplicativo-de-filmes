package com.agenda.aplicativodefilmes.repository

import com.agenda.aplicativodefilmes.api.RetrofitServices
import com.agenda.aplicativodefilmes.model.Categorias
import retrofit2.Call


class MoviesRepository(private val retrofitServices: RetrofitServices) {
    fun getAllCategorias() = retrofitServices.listaCategorias()
}