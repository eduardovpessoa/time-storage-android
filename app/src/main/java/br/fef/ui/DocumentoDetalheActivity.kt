package br.fef.ui

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Documento
import br.fef.data.api.dto.Imagem
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_documento_detalhe.*
import kotlinx.android.synthetic.main.content_documento_detalhe.*
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
        request.enqueue(object : Callback<List<Documento>> {
            override fun onFailure(call: Call<List<Documento>>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
                Toast.makeText(applicationContext, "Não foi possível carregar o documento!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Documento>>, response: Response<List<Documento>>?) {
                if (response?.code() == 200) {
                    val documento = response.body()?.get(0)
                    txtDetalheTitulo.text = documento?.titulo
                    txtDetalheAutor.text = documento?.autor
                    txtDetalheCategoria.text = documento?.categoria
                    txtDetalheEditora.text = documento?.editora
                    txtDetalhePublicacao.text = documento?.dataPublicacao
                    txtDetalheSinopse.text = documento?.sinopse
                }
            }
        })

        val requestFotos = api.getAllImagens(cod)
        requestFotos.enqueue(object : Callback<List<Imagem>> {
            override fun onResponse(call: Call<List<Imagem>>, response: Response<List<Imagem>>?) {
                if (response?.code() == 200) {
                    val imagens = response.body()
                    imagens?.forEach {
                        val imageView = ImageView(applicationContext)
                        imageView.layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.WRAP_CONTENT
                        )
                        Glide.with(applicationContext).load(it.caminhoImagem).into(imageView)
                        llGaleraFotos.addView(imageView)
                    }
                }
            }

            override fun onFailure(call: Call<List<Imagem>>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
                Toast.makeText(applicationContext, "Não foi possível carregar o documento!", Toast.LENGTH_LONG).show()
            }

        })
    }
}
