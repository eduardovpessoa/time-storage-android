package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("id_categoria")
    val id: Int,
    @SerializedName("descricao_categoria")
    val descricao: String,
    @SerializedName("status_categoria")
    val status: Int
)