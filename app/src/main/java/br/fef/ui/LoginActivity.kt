package br.fef.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Login
import kotlinx.android.synthetic.main.activity_login.*
import br.fef.data.persistence.AppDatabase
import br.fef.data.persistence.dao.UserDao
import br.fef.data.persistence.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest


class LoginActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appDatabase = AppDatabase.getDatabase(this)
        userDao = appDatabase.userDao()
        btnLogin.setOnClickListener {
            if (validateFields()) {
                doLogin()
            }
        }
        txtLoginCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
        val userList: List<User> = userDao.queryAll()
        if (!userList.isNullOrEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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
        val api = TimeStorageApi()
        val request: Call<User> = api.doLogin(Login(edtUsuario.text.toString(), edtSenha.text.toString().hash()))
        request.enqueue(object : Callback<User?> {
            override fun onFailure(call: Call<User?>, t: Throwable?) {
                showError("Dados incorretos!")
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<User?>, response: Response<User?>?) {
                if (response?.code() == 200) {
                    response.body()?.let {
                        val user: User = it
                        salvarUsuario(user)
                    }
                } else {
                    showError("Dados incorretos!")
                }
            }
        })
    }

    private fun salvarUsuario(user: User) {
        userDao.deleteAll()
        userDao.insert(user)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun String.isValidEmail(): Boolean {
        return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isValidPassword(): Boolean {
        return this.isNotEmpty() && this.length > 1
    }

    private fun String.hash(): String {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    fun checkConnection(): Boolean? {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected
    }

    fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}
