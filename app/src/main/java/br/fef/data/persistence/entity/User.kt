package br.fef.data.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey var cod: Int,
    var nome: String,
    var email: String,
    var tipo: Int
)