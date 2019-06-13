package br.fef.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.fef.R
import br.fef.data.api.dto.Editora

class EditoraAdapter(var editoraList: List<Editora>?) : RecyclerView.Adapter<EditoraAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(editoraList?.get(position), position % 2 != 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return editoraList?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.txtRow)
        fun bindView(editora: Editora?, colorido: Boolean) {
            title.text = editora!!.descricao
            if (editora.status == 1)
                title.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            if (colorido)
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.backgroundGrey))
        }
    }
}