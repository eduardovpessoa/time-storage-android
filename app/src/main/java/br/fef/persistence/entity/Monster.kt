package br.fef.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Monster(@PrimaryKey(autoGenerate = true) var id: Long)