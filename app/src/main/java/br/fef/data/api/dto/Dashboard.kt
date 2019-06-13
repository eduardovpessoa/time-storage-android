package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Dashboard(
    @SerializedName("documentos") val documentos: Int,
    @SerializedName("autores") val autores: Int,
    @SerializedName("generos") val generos: Int,
    @SerializedName("usuarios") val usuarios: Int
)