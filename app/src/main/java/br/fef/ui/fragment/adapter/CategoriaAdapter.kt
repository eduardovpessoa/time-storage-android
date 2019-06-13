package br.fef.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.fef.R
import br.fef.data.api.dto.Categoria

class CategoriaAdapter(var categoriaList: List<Categoria>?) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(categoriaList?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoriaList?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.txtRow)
        fun bindView(categoria: Categoria?) {
            title.text = categoria!!.descricao
            if (categoria.status == 1)
                title.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
        }
    }
}