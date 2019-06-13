package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Genero(
    @SerializedName("id_genero") val id: Int,
    @SerializedName("descricao_genero") val descricao: String,
    @SerializedName("status_genero") val status: Int
)