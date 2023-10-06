package com.agenda.aplicativodefilmes.api

import com.agenda.aplicativodefilmes.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    //Buscando os dados
    @GET("/filmes")
    fun listaCategoria(): Call<Categorias>
}