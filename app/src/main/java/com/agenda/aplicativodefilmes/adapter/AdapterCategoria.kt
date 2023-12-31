package com.agenda.aplicativodefilmes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agenda.aplicativodefilmes.databinding.CategoriaItemBinding
import com.agenda.aplicativodefilmes.model.Categoria

class AdapterCategoria(
    private val context: Context,
    var listaCategoria: MutableList<Categoria> = mutableListOf()
) : RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>() {
    inner class CategoriaViewHolder(binding: CategoriaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tituloCategoria = binding.txtCategoria
        val recyclerViewFilmes = binding.recyclerViewFIlmes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val itemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoriaViewHolder(itemLista)
    }

    override fun getItemCount() = listaCategoria.size

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = listaCategoria[position]
        holder.tituloCategoria.text = categoria.titulo

        val adapterFilmes = AdapterFilme(context, categoria.filmes)
        holder.recyclerViewFilmes.adapter = adapterFilmes
        holder.recyclerViewFilmes.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}