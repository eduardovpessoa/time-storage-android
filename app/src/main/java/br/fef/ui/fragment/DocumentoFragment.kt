package br.fef.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.DocumentoSimplificado
import br.fef.ui.DocumentoDetalheActivity
import br.fef.ui.fragment.adapter.DocumentoSimplificadoAdapter
import br.fef.ui.fragment.adapter.DocumentoSimplificadoListener
import kotlinx.android.synthetic.main.fragment_generic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentoFragment : Fragment() {

    private var documentoSimplificadoList: List<DocumentoSimplificado> = ArrayList()
    private lateinit var listener: DocumentoSimplificadoListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerGeneric)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        val api = TimeStorageApi()
        val request: Call<List<DocumentoSimplificado>> = api.getAllDocumentos()
        request.enqueue(object : Callback<List<DocumentoSimplificado>> {
            override fun onFailure(call: Call<List<DocumentoSimplificado>>?, t: Throwable?) {
                showError("Problemas ao realizar o registro! ${t?.message}")
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(
                call: Call<List<DocumentoSimplificado>>?,
                response: Response<List<DocumentoSimplificado>>?
            ) {
                if (response?.code() == 200) {
                    documentoSimplificadoList = response.body()!!
                    recyclerGeneric.adapter = DocumentoSimplificadoAdapter(documentoSimplificadoList,
                        object : DocumentoSimplificadoListener {
                            override fun onClickListener(cod: Int?) {
                                var intent = Intent(view.context, DocumentoDetalheActivity::class.java)
                                intent.putExtra("cod", cod)
                                startActivityForResult(intent, REQUEST_DETALHES)
                            }
                        })
                }
            }
        })

    }

    fun showError(message: String) {
        Toast.makeText(view?.context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val REQUEST_DETALHES: Int = 9091
    }
}
