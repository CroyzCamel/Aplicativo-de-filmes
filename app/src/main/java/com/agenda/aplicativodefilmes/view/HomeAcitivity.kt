package com.agenda.aplicativodefilmes.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.adapter.AdapterCategoria
import com.agenda.aplicativodefilmes.api.Api
import com.agenda.aplicativodefilmes.databinding.ActivityHomeAcitivityBinding
import com.agenda.aplicativodefilmes.model.Categoria
import com.agenda.aplicativodefilmes.model.Categorias
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeAcitivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAcitivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapterCategoria: AdapterCategoria
    private val listaCategoria: MutableList<Categoria> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerViewCategoria
        val recyclerViewCaterogia = binding.recyclerViewCategoria
        recyclerViewCaterogia.layoutManager = LinearLayoutManager(this@HomeAcitivity)
        adapterCategoria = AdapterCategoria(this@HomeAcitivity,listaCategoria)
        recyclerViewCaterogia.setHasFixedSize(true)
        recyclerViewCaterogia.adapter = adapterCategoria

        //Deslogar usuário.
        binding.txtSair.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@HomeAcitivity, LoginActivity::class.java))
            finish()
            Toast.makeText(this,"Você deslogou com sucesso",Toast.LENGTH_LONG).show()

        }
        //Configuração retrofit
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(Api::class.java)

        retrofit.listaCategoria().enqueue(object : Callback<Categorias>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
               if (response.code() == 200) {
                   response.body()?.let {
                        adapterCategoria.listaCategoria.addAll(it.categorias)
                       adapterCategoria.notifyDataSetChanged()
                       //ProgressBar
                       binding.containerProrgessbar.visibility = View.GONE
                       binding.progressBar.visibility = View.GONE
                       binding.txtCarregando.visibility = View.GONE

                   }
               }
            }

            override fun onFailure(call: Call<Categorias>, t: Throwable) {

            }

        })
    }
}