package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName
import java.lang.Exception
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
        try {
            var newDataNasc: String = ""
            var newDataMorte: String = ""

            if (!dataNascimento.isNullOrEmpty()) {
                newDataNasc = SimpleDateFormat("yyyy", Locale("pt", "BR")).format(
                    SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).parse(dataNascimento)
                )
            }
            if (!dataFalecimento.isNullOrEmpty()) {
                newDataMorte = SimpleDateFormat("yyyy", Locale("pt", "BR")).format(
                    SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).parse(dataFalecimento)
                )
            }
            return if (newDataNasc.isEmpty() && newDataMorte.isEmpty())
                nome
            else if (newDataNasc.isNotEmpty() && newDataMorte.isNotEmpty())
                "$nome ($newDataNasc - $newDataMorte)"
            else
                "$nome ($newDataNasc)"
        } catch (e: Exception) {
            return nome
        }
    }
}