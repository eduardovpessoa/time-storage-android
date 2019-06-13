package br.fef.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import br.fef.R
import kotlinx.android.synthetic.main.fragment_genero.*


class GeneroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val values = arrayOf(
            "Documentário",
            "Estudo Científico",
            "Histórico"
        )

        val adapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, values)
        lst_view_genero.adapter = adapter
    }


}
