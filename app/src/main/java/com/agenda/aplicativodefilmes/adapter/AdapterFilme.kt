package com.agenda.aplicativodefilmes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agenda.aplicativodefilmes.databinding.FilmesBinding
import com.agenda.aplicativodefilmes.model.Filmes
import com.agenda.aplicativodefilmes.view.DetalhesFilme
import com.bumptech.glide.Glide

class AdapterFilme(private val context: Context, private val listaFilmes: MutableList<Filmes>) :
    RecyclerView.Adapter<AdapterFilme.FilmeViewHolder>() {
    inner class FilmeViewHolder(binding: FilmesBinding) : RecyclerView.ViewHolder(binding.root) {
        val capa = binding.capaFilme
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemLista = FilmesBinding.inflate(LayoutInflater.from(context), parent, false)
        return FilmeViewHolder(itemLista)
    }

    override fun getItemCount() = listaFilmes.size
    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        Glide.with(context).load(listaFilmes[position].capa).centerCrop().into(holder.capa)
        holder.capa.setOnClickListener {
            val intent = Intent(context, DetalhesFilme::class.java)
            intent.putExtra("capa", listaFilmes[position].capa)
            intent.putExtra("nome", listaFilmes[position].nome)
            intent.putExtra("descricao", listaFilmes[position].descricao)
            intent.putExtra("elenco", listaFilmes[position].elenco)
            context.startActivity(intent)
        }
    }
}
