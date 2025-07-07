package com.example.clineapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nickname: String,
    val birthday: String // "yyyy-MM-dd" format
)
