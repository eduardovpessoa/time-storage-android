package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class DocumentoSimplificado(
    @SerializedName("caminho_imagem") val caminhoImagem: String,
    @SerializedName("data_publicacao_documento") val dataPublicacao: String,
    @SerializedName("descricao_categoria") val categoria: String,
    @SerializedName("descricao_editora") val editora: String,
    @SerializedName("id_documento") val id: Int,
    @SerializedName("nome_autor") val autor: String,
    @SerializedName("titulo_documento") val titulo: String
)