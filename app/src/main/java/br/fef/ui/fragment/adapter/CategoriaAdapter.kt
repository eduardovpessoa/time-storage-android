package br.fef.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.fef.R
import br.fef.data.api.dto.Categoria
import kotlinx.android.synthetic.main.row_categoria.view.*

class CategoriaAdapter(private val categoriaList: List<Categoria>, private val context: Context) :
    RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categoriaList[position]
        holder.bindView(categoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_categoria, parent, false))
    }

    override fun getItemCount(): Int {
        return categoriaList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(categoria: Categoria) {
            val title = itemView.txtRowCategoria
            title.text = categoria.toString()
        }
    }
}