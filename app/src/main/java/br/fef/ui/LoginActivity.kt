package br.fef.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.fef.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*private fun isEmailValid(email: String): Boolean {
            //TODO: Replace this with your own logic
            return email.contains("@")
        }

        private fun isPasswordValid(password: String): Boolean {
            //TODO: Replace this with your own logic
            return password.length > 4
        }*/

    }
}
