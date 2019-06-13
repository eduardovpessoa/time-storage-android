package br.fef.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Register
import br.fef.data.persistence.AppDatabase
import br.fef.data.persistence.dao.UserDao
import kotlinx.android.synthetic.main.activity_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        appDatabase = AppDatabase.getDatabase(this)
        userDao = appDatabase.userDao()
        btnCadastroSalvar.setOnClickListener {
            if (validaCampos()) {
                register()
            }

        }
    }

    private fun validaCampos(): Boolean {
        if (edtCadastroNome.text.toString().isEmpty()) {
            edtCadastroNome.requestFocus()
            edtCadastroNome.error = "Informe um nome válido!"
            return false
        } else {
            edtCadastroNome.error = null
            edtCadastroNome.clearFocus()
        }
        if (edtCadastroSobrenome.text.toString().isEmpty()) {
            edtCadastroSobrenome.requestFocus()
            edtCadastroSobrenome.error = "Informe um sobrenome válido!"
            return false
        } else {
            edtCadastroSobrenome.error = null
            edtCadastroSobrenome.clearFocus()
        }
        if (edtCadastroTelefone.text.toString().isEmpty()) {
            edtCadastroTelefone.requestFocus()
            edtCadastroTelefone.error = "Informe um telefone válido!"
            return false
        } else {
            edtCadastroTelefone.error = null
            edtCadastroTelefone.clearFocus()
        }
        if (edtCadastroNascimento.text.toString().isEmpty()) {
            edtCadastroNascimento.requestFocus()
            edtCadastroNascimento.error = "Informe a data de nascimento!"
            return false
        } else {
            edtCadastroNascimento.error = null
            edtCadastroNascimento.clearFocus()
        }
        if (!edtCadastroEmail.text.toString().isValidEmail()) {
            edtCadastroEmail.requestFocus()
            edtCadastroEmail.error = "Informe um e-mail válido!"
            return false
        } else {
            edtCadastroEmail.error = null
            edtCadastroEmail.clearFocus()
        }
        if (!edtCadastroSenha.text.toString().isValidPassword()) {
            edtCadastroSenha.requestFocus()
            edtCadastroSenha.error = "A senha deve possuir no mínimo 4 caracteres!"
            return false
        } else {
            edtCadastroSenha.error = null
            edtCadastroSenha.clearFocus()
        }
        if (!edtConfirmarSenha.text.toString().isValidPassword()) {
            edtConfirmarSenha.requestFocus()
            edtConfirmarSenha.error = "A senha deve possuir no mínimo 4 caracteres!"
            return false
        } else {
            edtConfirmarSenha.error = null
            edtConfirmarSenha.clearFocus()
        }
        if (edtConfirmarSenha.text.toString() != edtCadastroSenha.text.toString()) {
            edtConfirmarSenha.requestFocus()
            edtConfirmarSenha.error = "As senhas não coincidem!"
            return false
        } else {
            edtConfirmarSenha.error = null
            edtConfirmarSenha.clearFocus()
        }
        return true
    }

    private fun String.isValidEmail(): Boolean {
        return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isValidPassword(): Boolean {
        return this.isNotEmpty() && this.length > 1
    }

    private fun register() {
        val api = TimeStorageApi()
        val request: Call<Void> = api.doRegister(
            Register(
                edtCadastroNome.text.toString(),
                edtCadastroSobrenome.text.toString(),
                edtCadastroTelefone.text.toString(),
                formatDate(edtCadastroNascimento.text.toString()),
                edtCadastroEmail.text.toString(),
                edtCadastroSenha.text.toString().hash()
            )
        )

        request.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable?) {
                showError("Problemas ao salvar o registro! ${t?.message}")
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>?) {
                if (response?.code() == 200 || response?.code() == 201) {
                    showError("Cadastrado com sucesso! Faça o Login!")
                    finish()
                }
            }
        })

    }

    private fun String.hash(): String {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun formatDate(dataNasc: String): String {
        val date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(dataNasc)
        return SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).format(date)
    }


}


