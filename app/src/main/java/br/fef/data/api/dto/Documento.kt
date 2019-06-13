package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Documento(
    @SerializedName("data_inclusao_documento") val dataInclusao: String,
    @SerializedName("data_publicacao_documento") val dataPublicacao: String,
    @SerializedName("descricao_categoria") val categoria: String,
    @SerializedName("descricao_editora") val editora: String,
    @SerializedName("id_documento") val id: Int,
    @SerializedName("id_usuario") val idUsuario: Int,
    @SerializedName("nome_autor") val autor: String,
    @SerializedName("sinopse_documento") val sinopse: String,
    @SerializedName("status_documento") val status: Int,
    @SerializedName("titulo_documento") val titulo: String
)