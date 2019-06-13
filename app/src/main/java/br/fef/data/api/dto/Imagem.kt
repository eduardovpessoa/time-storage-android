package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Imagem(
    @SerializedName("caminho_imagem") val caminhoImagem: String,
    @SerializedName("id_imagem") val id: Int,
    @SerializedName("descricao_imagem") val descricao: String,
    @SerializedName("id_documento") val idDocumento: Int
)