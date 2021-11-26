package com.example.sql.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val sobrenome: String,
    val idade: Int
)