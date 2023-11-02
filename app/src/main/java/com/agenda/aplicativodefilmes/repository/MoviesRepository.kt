package com.agenda.aplicativodefilmes.repository

import com.agenda.aplicativodefilmes.api.RetrofitServices


class MoviesRepository(private val retrofitServices: RetrofitServices) {
    fun getAllCategorias() = retrofitServices.listaCategorias()
}