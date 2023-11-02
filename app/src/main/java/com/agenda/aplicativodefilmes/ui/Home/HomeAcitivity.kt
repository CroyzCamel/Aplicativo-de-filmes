package com.agenda.aplicativodefilmes.ui.Home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agenda.aplicativodefilmes.adapter.AdapterCategoria
import com.agenda.aplicativodefilmes.api.RetrofitServices
import com.agenda.aplicativodefilmes.databinding.ActivityHomeAcitivityBinding
import com.agenda.aplicativodefilmes.factory.MoviesViewModelFactory
import com.agenda.aplicativodefilmes.model.Categoria
import com.agenda.aplicativodefilmes.repository.MoviesRepository
import com.agenda.aplicativodefilmes.ui.Login.LoginActivity
import com.agenda.aplicativodefilmes.viewmodel.MoviesViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeAcitivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAcitivityBinding
    private lateinit var adapterCategoria: AdapterCategoria
    private lateinit var viewModel: MoviesViewModel
    private val retrofitService = RetrofitServices.getInstance()
    private var listaCategoria: MutableList<Categoria> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Progressbar
        binding.progressBar.visibility = View.VISIBLE
        //txtCarregando
        binding.txtCarregando.visibility = View.VISIBLE

        //Deslogar usuário.
        binding.txtSair.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@HomeAcitivity, LoginActivity::class.java))
            finish()
            Toast.makeText(this,"Você deslogou com sucesso",Toast.LENGTH_LONG).show()

        }
        //RecyclerViewCategoria
        adapterCategoria = AdapterCategoria(this,listaCategoria) // Inicializa com uma lista vazia
        val recyclerViewCategoria = binding.recyclerViewCategoria
        recyclerViewCategoria.layoutManager = LinearLayoutManager(this)
        recyclerViewCategoria.adapter = adapterCategoria




        viewModel = ViewModelProvider(
            this,
            MoviesViewModelFactory(MoviesRepository(retrofitService)))[MoviesViewModel::class.java]

        observers()
        viewModel.getAllCategorias()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observers() {
        viewModel.categorias.observe(this@HomeAcitivity, Observer { categorias ->
           adapterCategoria.listaCategoria = categorias.categorias
            adapterCategoria.notifyDataSetChanged()

            binding.progressBar.visibility = View.GONE
            binding.txtCarregando.visibility = View.GONE
        })
    }
}