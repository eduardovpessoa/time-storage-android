package br.fef.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.fef.R
import br.fef.data.api.dto.DocumentoSimplificado
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class DocumentoSimplificadoAdapter(
    var documentoSimplificadoList: List<DocumentoSimplificado>?,
    val clickListener: DocumentoSimplificadoListener
) :
    RecyclerView.Adapter<DocumentoSimplificadoAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(documentoSimplificadoList?.get(position), position % 2 != 0, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_documentos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return documentoSimplificadoList?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.imgDocumento)
        var title: TextView = itemView.findViewById(R.id.txtDocTitulo)
        var autor: TextView = itemView.findViewById(R.id.txtDocAutor)
        var categoria: TextView = itemView.findViewById(R.id.txtDocCategoria)
        var editora: TextView = itemView.findViewById(R.id.txtDocEditora)
        var publicacao: TextView = itemView.findViewById(R.id.txtDocPublicacao)

        fun bindView(
            documentoSimplificado: DocumentoSimplificado?,
            colorido: Boolean,
            listener: DocumentoSimplificadoListener
        ) {
            title.text = documentoSimplificado?.titulo
            autor.text = documentoSimplificado?.autor
            categoria.text = documentoSimplificado?.categoria
            editora.text = documentoSimplificado?.editora
            if (!documentoSimplificado?.dataPublicacao.isNullOrEmpty()) {
                publicacao.text = formatDate(documentoSimplificado?.dataPublicacao)
            } else {
                publicacao.text = "N/A"
            }
            Glide.with(itemView.context)
                .load("http://timestorage.ddns.net:8080/img${documentoSimplificado?.caminhoImagem}")
                .into(img)
            if (colorido)
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.backgroundGreyDark))
            else
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.backgroundGrey))
            itemView.setOnClickListener {
                listener.onClickListener(documentoSimplificado?.id)
            }
        }

        private fun formatDate(data: String?): String {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).parse(data)
            return SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(date)
        }
    }

}