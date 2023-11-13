package com.agenda.aplicativodefilmes.services

interface LoginListener {
    fun onSucess()
    fun onError(text: String)
}