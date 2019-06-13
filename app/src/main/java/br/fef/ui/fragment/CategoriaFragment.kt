package br.fef.ui.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import br.fef.R
import br.fef.data.api.TimeStorageApi
import br.fef.data.api.dto.Categoria
import br.fef.ui.fragment.adapter.CategoriaAdapter
import kotlinx.android.synthetic.main.fragment_generic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriaFragment : Fragment() {

    private var categoriaList: List<Categoria> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerGeneric)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        val api = TimeStorageApi()
        val request: Call<List<Categoria>> = api.getAllCategorias()
        request.enqueue(object : Callback<List<Categoria>> {
            override fun onFailure(call: Call<List<Categoria>>?, t: Throwable?) {
                showError("Problemas ao realizar o registro! ${t?.message}")
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<List<Categoria>>?, response: Response<List<Categoria>>?) {
                if (response?.code() == 200) {
                    categoriaList = response.body()!!
                    recyclerGeneric.adapter = CategoriaAdapter(categoriaList)
                }
            }
        })

    }

    fun showError(message: String) {
        Toast.makeText(view?.context, message, Toast.LENGTH_LONG).show()
    }


}
