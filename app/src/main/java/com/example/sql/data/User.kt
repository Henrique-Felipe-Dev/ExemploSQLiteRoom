package com.example.sql.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val raca: String,
    val sexo: String,
    val idade: Int,
    val dano: Double,
    val hp: Double
)