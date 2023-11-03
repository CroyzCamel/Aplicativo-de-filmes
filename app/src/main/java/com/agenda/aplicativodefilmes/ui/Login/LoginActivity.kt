package com.agenda.aplicativodefilmes.ui.Login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.databinding.ActivityLoginBinding
import com.agenda.aplicativodefilmes.factory.LoginViewModelFactory
import com.agenda.aplicativodefilmes.repository.UserRepository
import com.agenda.aplicativodefilmes.ui.Cadastro.RegisterActivity
import com.agenda.aplicativodefilmes.ui.Home.HomeAcitivity
import com.agenda.aplicativodefilmes.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.bege_100)
        //Dar um start inicial diretamente no editEmail
        binding.editEmail.requestFocus()
        viewModel = ViewModelProvider(this, LoginViewModelFactory(UserRepository()))[LoginViewModel::class.java]


        binding.btLogin.setOnClickListener{


            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            when {
                email.isEmpty() -> {
                    binding.editLayoutEmail.helperText = "Coloca o email que tu cadastro!"
                    binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#B71C1C")
                }
                senha.isEmpty() -> {
                    binding.editLayoutSenha.helperText = "Coloca aquela senha que tu anotou no cadastro"
                    binding.editLayoutSenha.boxStrokeColor = Color.parseColor("#B71C1C")
                } else -> {
                userLogin(email, senha)
                }
            }
        }

        binding.txtIrCadastro.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }

        observer()
    }

    private fun observer (){
        viewModel.login.observe(this){login ->
            if (login){
                startActivity(Intent(this@LoginActivity,HomeAcitivity::class.java))
                finish()
            }
        }
    }

    private fun userLogin (email:String?, password: String?) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            // Lida com o caso em que email ou senha é nulo ou vazio
            Toast.makeText(this@LoginActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.userSign(email, password )
    }

    override fun onStart() {
        super.onStart()
        viewModel.currentUser()

        viewModel.currentUser.observe(this@LoginActivity){ currentUser ->
            if (currentUser){
                    startActivity(Intent(this@LoginActivity,HomeAcitivity::class.java))
            }
        }
    }
}