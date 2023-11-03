
package com.agenda.aplicativodefilmes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.databinding.ActivityDetalhesFilmeBinding
import com.bumptech.glide.Glide

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.bege_100)
        val capa = intent.extras?.getString("capa")
        val nome = intent.extras?.getString("nome")
        val descricao = intent.extras?.getString("descricao")
        val elenco = intent.extras?.getString("elenco")


        Glide.with(this@DetalhesFilme).load(capa).centerCrop().into(binding.capaDoFilme)
        binding.txtNomeFilme.setText(nome)
        binding.txtDescricao.setText("Descrição: $descricao")
        binding.txtElenco.setText("Elenco: $elenco")


    }
}