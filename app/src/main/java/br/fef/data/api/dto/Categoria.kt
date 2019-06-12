package br.fef.data.api.dto

data class Categoria(
    private val idCategoria: Int,
    private val descricaoCategoria: String,
    private val statusCategoria: Int
) {
    override fun toString(): String {
        return descricaoCategoria
    }
}