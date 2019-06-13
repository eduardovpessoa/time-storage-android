package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Editora(
    @SerializedName("id_editora") val id: Int,
    @SerializedName("descricao_editora") val descricao: String,
    @SerializedName("status_editora") val status: Int
)