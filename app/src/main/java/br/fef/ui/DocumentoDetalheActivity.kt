package br.fef.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Documento

import kotlinx.android.synthetic.main.activity_documento_detalhe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentoDetalheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documento_detalhe)
        setSupportActionBar(toolbar)

        val cod = intent?.extras?.getInt("cod")

        fabDocumentoDetalhe.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val api = TimeStorageApi()
        val request = api.getDocumentoDetail(cod)
        request.enqueue(object : Callback<Documento> {
            override fun onFailure(call: Call<Documento>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
                Toast.makeText(applicationContext, "Não foi possível carregar o documento!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Documento>, response: Response<Documento>?) {
                if (response?.code() == 200) {
                    //TODO POPULAR CAMPOS
                }
            }
        })
    }
}
