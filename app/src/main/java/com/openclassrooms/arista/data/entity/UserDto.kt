package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDto(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userById")
    var id : Long = 0,

    @ColumnInfo(name = "nom")
    var nom : String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password : String





)
