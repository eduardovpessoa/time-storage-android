package br.fef.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Autor
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_cadastro_generic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CadastrarAutorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_generic)

        txtDataNascGeneric.visibility = TextInputLayout.VISIBLE
        txtDataMorteGeneric.visibility = TextInputLayout.VISIBLE

        btnSalvarGenerico.setOnClickListener {
            if (edtDescricaoGeneric.text.toString().isEmpty()) {
                edtDescricaoGeneric.error = "O nome/descrição é obrigatório!"
            } else {
                val api = TimeStorageApi()
                val request = api.sendAutor(
                    Autor(
                        formatDate(edtDataMorteGeneric.text.toString()),
                        formatDate(edtDataNascGeneric.text.toString()),
                        0,
                        edtDescricaoGeneric.text.toString(),
                        0
                    )
                )
                request.enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable?) {
                        showError("Problemas ao salvar o registro! ${t?.message}")
                        Log.e("onFailure error", t?.message)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>?) {
                        if (response?.code() == 200 || response?.code() == 201) {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }

                })
            }

        }

    }

    private fun formatDate(data: String): String {
        if (data.isEmpty())
            return ""
        val date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(data)
        return SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).format(date)
    }

    fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
