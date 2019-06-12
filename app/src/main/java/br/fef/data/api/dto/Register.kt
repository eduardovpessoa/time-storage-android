package br.fef.data.api.dto

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("nome_pessoa") var nome: String,
    @SerializedName("sobrenome_pessoa") var sobrenome: String,
    @SerializedName("telefone_pessoa") var telefone: String,
    @SerializedName("data_nascimento_pessoa") var nascimento: String,
    @SerializedName("email_pessoa") var email: String,
    @SerializedName("senha_usuario") var senha: String
)