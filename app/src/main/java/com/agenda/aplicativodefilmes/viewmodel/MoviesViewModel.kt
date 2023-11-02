package com.agenda.aplicativodefilmes.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agenda.aplicativodefilmes.model.Categorias
import com.agenda.aplicativodefilmes.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
        private val _categorias = MutableLiveData<Categorias>()
        val categorias: LiveData<Categorias> = _categorias
        fun getAllCategorias() {
            moviesRepository.getAllCategorias().enqueue(object : Callback<Categorias>{
                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if (response.isSuccessful) {
                        val categorias = response.body()
                        _categorias.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Categorias>, t: Throwable) {

                }
            })
        }
    }