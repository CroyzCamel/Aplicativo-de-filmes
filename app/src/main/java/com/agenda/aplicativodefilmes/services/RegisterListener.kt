package com.agenda.aplicativodefilmes.services

interface RegisterListener {
    fun onSucess()
    fun onError(text: String)
}