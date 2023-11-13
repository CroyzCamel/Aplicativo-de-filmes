package com.agenda.aplicativodefilmes.ui.cadastro

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.agenda.aplicativodefilmes.R
import com.agenda.aplicativodefilmes.databinding.ActivityCadastroBinding
import com.agenda.aplicativodefilmes.ui.login.LoginActivity
import com.agenda.aplicativodefilmes.viewmodel.RegisterViewModel
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val viewModel: RegisterViewModel by inject()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.bege_100)
        //Dar um start inicial diretamente no editEmail
        binding.editEmail.requestFocus()

        binding.btBora.setOnClickListener {

            val email = binding.editEmail.text.toString()
            binding.editSenha.text.toString()

            if (email.isNotEmpty()) {
                binding.editLayoutSenha.visibility = View.VISIBLE
                binding.btBora.visibility = View.GONE
                binding.btCadastrar.visibility = View.VISIBLE
                binding.txtTitulo.text = "Agora todos os filmes em um só lugar \n bora lá então ?"
                binding.txtDescricao.text = "Vem fazer parte da tropa dos filmeros"
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#5E9FF2")
                binding.LayoutHeader.visibility = View.VISIBLE
            } else {
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#B71C1C")
                binding.editLayoutEmail.helperText = "Preencha com um email valido"
            }
        }

        binding.btCadastrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.editSenha.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registerUser(email, password)
            } else if (password.isEmpty()) {
                binding.editLayoutSenha.boxStrokeColor = Color.parseColor("#FF0000")
                binding.editLayoutSenha.helperText = "Tem que ter senha né, aproveita e anota"
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#5E9FF2")
            } else if (email.isEmpty()) {
                binding.editLayoutEmail.boxStrokeColor = Color.parseColor("#B71C1C")
                binding.editLayoutEmail.helperText = "Preencha com um email valido"
            }
        }
        binding.txtEntrar.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        observers()
    }
    private fun observers() {
        viewModel.register.observe(this@RegisterActivity) { register ->
            if (register) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
        viewModel.message.observe(this@RegisterActivity) { message ->
            Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}