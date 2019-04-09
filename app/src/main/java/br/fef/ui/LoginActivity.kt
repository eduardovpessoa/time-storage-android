package br.fef.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import br.fef.R
import kotlinx.android.synthetic.main.activity_login.*
import br.fef.model.Login
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.security.MessageDigest


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            if (validateFields()) {
                doLogin()
            }
        }
        txtLoginCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateFields(): Boolean {
        if (!edtUsuario.text.toString().isValidEmail()) {
            edtUsuario.requestFocus()
            edtUsuario.error = "Informe um e-mail válido!"
            return false
        } else {
            edtUsuario.error = null
            edtUsuario.clearFocus()
        }

        if (!edtSenha.text.toString().isValidPassword()) {
            edtSenha.requestFocus()
            edtSenha.error = "A senha deve possuir no mínimo 4 caracteres!"
            return false
        } else {
            edtSenha.error = null
            edtSenha.clearFocus()
        }
        return true
    }

    private fun doLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        /*val login = Login(edtUsuario.text.toString(), edtSenha.text.toString().hash())
        val json = Gson().toJson(login)
        val client = OkHttpClient()
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url("http://localhost:5000/login")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })*/
    }

    private fun String.isValidEmail(): Boolean {
        return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isValidPassword(): Boolean {
        return this.isNotEmpty() && this.length > 4
    }

    private fun String.hash(): String {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

}
