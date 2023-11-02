package com.agenda.aplicativodefilmes.model

import com.google.gson.annotations.SerializedName

data class Categoria(
    //SerializedName associa exatamente as "variaveis" da APi
    @SerializedName("titulo")
    val titulo: String? = null,
    //Capa aqui é a capa inicial antes do clique
    @SerializedName("capas")
    val filmes: MutableList<Filmes> = mutableListOf()
)

data class Filmes(
   @SerializedName("url_imagem")
   val capa: String? = null,
   @SerializedName("id")
   val id: Int = 0,
    val nome: String? = null,
    val descricao: String? = null,
    val elenco: String? = null
)

data class Categorias(@SerializedName("categoria")
    val categorias: MutableList<Categoria> = mutableListOf()
)
