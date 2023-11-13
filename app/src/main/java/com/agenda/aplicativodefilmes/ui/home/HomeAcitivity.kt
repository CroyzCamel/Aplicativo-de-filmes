package com.agenda.aplicativodefilmes.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.adapter.AdapterCategoria
import com.agenda.aplicativodefilmes.databinding.ActivityHomeAcitivityBinding
import com.agenda.aplicativodefilmes.model.Categoria
import com.agenda.aplicativodefilmes.ui.login.LoginActivity
import com.agenda.aplicativodefilmes.viewmodel.MoviesViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject

class HomeAcitivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAcitivityBinding
    private lateinit var adapterCategoria: AdapterCategoria
    val viewModel: MoviesViewModel by inject()
    private var listaCategoria: MutableList<Categoria> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.bege_100)
        //Progressbar
        binding.progressBar.visibility = View.VISIBLE
        //txtCarregando
        binding.txtCarregando.visibility = View.VISIBLE
        //Deslogar usuário.
        binding.txtSair.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@HomeAcitivity, LoginActivity::class.java))
            finish()
            Toast.makeText(this, "Você deslogou com sucesso", Toast.LENGTH_LONG).show()
        }
        //RecyclerViewCategoria
        adapterCategoria = AdapterCategoria(this, listaCategoria) // Inicializa com uma lista vazia
        val recyclerViewCategoria = binding.recyclerViewCategoria
        recyclerViewCategoria.layoutManager = LinearLayoutManager(this)
        recyclerViewCategoria.adapter = adapterCategoria

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