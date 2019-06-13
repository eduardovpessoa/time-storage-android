package br.fef.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Categoria
import kotlinx.android.synthetic.main.activity_cadastro_generic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarCategoriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_generic)

        btnSalvarGenerico.setOnClickListener {
            if (edtDescricaoGeneric.text.toString().isEmpty()) {
                edtDescricaoGeneric.error = "O nome/descrição é obrigatório!"
            } else {
                val api = TimeStorageApi()
                val request = api.sendCategoria(Categoria(0, edtDescricaoGeneric.text.toString(), 0))
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

    fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
