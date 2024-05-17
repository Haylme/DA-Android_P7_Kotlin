package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sleep", foreignKeys = [

        ForeignKey(
            entity = UserDto::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE

        )
    ]
)

data class SleepDto(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,


    @ColumnInfo(name = "debut") var startTime: Long,

    @ColumnInfo(name = "duree") var duration: Int,

    @ColumnInfo(name = "qualite") var quality: Int,

    @ColumnInfo(name = "user_id") var userId: Long

)
