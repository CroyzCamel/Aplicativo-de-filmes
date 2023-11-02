package com.agenda.aplicativodefilmes.api

import com.agenda.aplicativodefilmes.model.Categorias
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices {
    @GET("/filmes")
    fun listaCategorias(): Call<Categorias>

    companion object {
        private var retrofitServices: RetrofitServices? = null

        fun getInstance(): RetrofitServices {
            if (retrofitServices == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://stackmobile.com.br/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitServices = retrofit.create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }
}

