package com.agenda.aplicativodefilmes.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.bege_100)
        //Dar um start inicial diretamente no editEmail
        binding.editEmail.requestFocus()
        //inciando Firebase Auth
        auth = Firebase.auth

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
                loginUsuario(email, senha)
                }
            }
        }

        // Click/Intenção criada para o usuário se cadastrar
        binding.txtIrCadastro.setOnClickListener {
            startActivity(Intent(this@LoginActivity,CadastroActivity::class.java))
            finish()
        }
    }

    private fun loginUsuario (email:String?, senha: String?) {
        if (email.isNullOrEmpty() || senha.isNullOrEmpty()) {
            // Lida com o caso em que email ou senha é nulo ou vazio
            Toast.makeText(this@LoginActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }
                auth.signInWithEmailAndPassword(email.toString(), senha.toString())
                    .addOnCompleteListener(this@LoginActivity) { login ->
                        if (login.isSuccessful) {
                            //Intenção para a tela incial do usuário.
                            startActivity(Intent(this@LoginActivity, HomeAcitivity::class.java))
                            finish()
                        }
                    }

    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = auth.currentUser
        if (usuarioAtual != null) {
            startActivity(Intent(this@LoginActivity, HomeAcitivity::class.java))
        }

    }
}