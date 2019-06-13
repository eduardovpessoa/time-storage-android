package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Autor(
    @SerializedName("data_falecimento_autor") val dataFalecimento: String,
    @SerializedName("data_nascimento_autor") val dataNascimento: String,
    @SerializedName("id_autor") val id: Int,
    @SerializedName("nome_autor") val nome: String,
    @SerializedName("status_autor") val status: Int
) {
    override fun toString(): String {
        val newData: String = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(dataNascimento).toString()
        return "$nome ($newData)"
    }
}