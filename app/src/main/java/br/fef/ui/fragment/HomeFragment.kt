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
import br.fef.data.api.dto.Dashboard
import br.fef.data.api.dto.Genero
import br.fef.ui.fragment.adapter.GeneroAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_generic.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var dashboard: Dashboard

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = TimeStorageApi()
        val request: Call<List<Dashboard>> = api.getDashboard()
        request.enqueue(object : Callback<List<Dashboard>> {
            override fun onFailure(call: Call<List<Dashboard>>?, t: Throwable?) {
                showError("Problemas ao realizar o registro! ${t?.message}")
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<List<Dashboard>>?, response: Response<List<Dashboard>>?) {
                if (response?.code() == 200) {
                    dashboard = response.body()!![0]
                    txtCardDocNumber.text = dashboard.documentos.toString()
                    txtCardAutorNumber.text = dashboard.autores.toString()
                    txtCardUserNumber.text = dashboard.usuarios.toString()
                    txtCardGeneroNumber.text = dashboard.generos.toString()
                }
            }
        })

    }

    fun showError(message: String) {
        Toast.makeText(view?.context, message, Toast.LENGTH_LONG).show()
    }
}
