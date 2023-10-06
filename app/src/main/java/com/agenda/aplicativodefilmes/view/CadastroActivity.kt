package com.agenda.aplicativodefilmes.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.databinding.ActivityCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Dar um start inicial diretamente no editEmail
        binding.editEmail.requestFocus()
        auth = Firebase.auth

        binding.btBora.setOnClickListener{

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if(email.isNotEmpty()) {
                binding.editLayoutSenha.visibility = View.VISIBLE
                binding.btBora.visibility = View.GONE
                binding.btCadastrar.visibility = View.VISIBLE
                binding.txtTitulo.setText("Agora todos os filmes em um só lugar \n bora lá então ?")
                binding.txtDescricao.setText("Vem fazer parte da tropa dos filmeros")
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#5E9FF2")
                binding.LayoutHeader.visibility = View.VISIBLE
            } else {
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#B71C1C")
                binding.editLayoutEmail.helperText = "Preencha com um email valido"
            }
        }

        binding.btCadastrar.setOnClickListener{

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()){
                criarConta(email,senha)
            } else if(senha.isEmpty()) {
                binding.editLayoutSenha.boxStrokeColor = Color.parseColor("#FF0000")
                binding.editLayoutSenha.helperText = "Tem que ter senha né, aproveita e anota"
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#5E9FF2")
            } else if(email.isEmpty()){
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#B71C1C")
                binding.editLayoutEmail.helperText = "Preencha com um email valido"
            }
        }

        binding.txtEntrar.setOnClickListener{
            startActivity(Intent(this@CadastroActivity, LoginActivity::class.java))
        }
    }

    private fun criarConta(email: String, senha:String){
        auth.createUserWithEmailAndPassword(email,senha)
            .addOnCompleteListener(this@CadastroActivity) { autenticacao ->
                if  (autenticacao.isSuccessful) {
                    startActivity(Intent(this@CadastroActivity,LoginActivity::class.java ))
                    Toast.makeText(this,"Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()

                    finish()
                }
            }.addOnFailureListener {erro ->
                when {
                    erro is FirebaseAuthWeakPasswordException -> {
                        binding.editLayoutSenha.helperText = "Digita uma senha melhor cabra bom"
                        binding.editLayoutSenha.boxStrokeColor = Color.parseColor("#FF0000")
                    }
                    erro is FirebaseAuthUserCollisionException -> {
                        binding.editLayoutEmail.helperText = "Esse email ai os cara já pegaram"
                        binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#FF0000")
                    }
                    erro is FirebaseNetworkException -> {
                        binding.editLayoutSenha.helperText = "Como tu vai cadastrar sem Internet homi!?, bota credito ai"
                        binding.editLayoutSenha.boxStrokeColor = Color.parseColor("#FF0000")
                    }

                }

            }

    }
}