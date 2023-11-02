package com.agenda.aplicativodefilmes.database

import com.agenda.aplicativodefilmes.services.LoginListener
import com.agenda.aplicativodefilmes.services.RegisterListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Database {
  private val auth = Firebase.auth


    fun userSign (email: String, password: String, listener: LoginListener){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { auth ->
                if(auth.isSuccessful){
                    listener.onSucess()
                }
            }.addOnFailureListener {
                listener.onError("Error ao realizar o Login")
            }
    }
    fun currentuser (listener: LoginListener){
        val currentuser = auth.currentUser
        if (currentuser != null){
            listener.onSucess()
        }
    }
    fun userRegister (email: String, password: String, listener: RegisterListener){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { register ->
            if (register.isSuccessful){
                listener.onSucess()

            }

        }.addOnFailureListener{
            listener.onError("Cadastrar usuário")
        }
    }


}
