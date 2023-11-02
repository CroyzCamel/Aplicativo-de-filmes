package com.agenda.aplicativodefilmes.repository

import com.agenda.aplicativodefilmes.database.Database
import com.agenda.aplicativodefilmes.services.LoginListener
import com.agenda.aplicativodefilmes.services.RegisterListener

class UserRepository {
    val database = Database()

    fun userSignRepository (email: String, password: String, listener: LoginListener){
        database.userSign(email, password, listener)
    }

    fun userRegisterRepository(email: String,password: String,listener: RegisterListener){
        database.userRegister(email, password, listener)
    }

    fun currentUserRepository(listener: LoginListener){
        database.currentuser(listener)
    }


}