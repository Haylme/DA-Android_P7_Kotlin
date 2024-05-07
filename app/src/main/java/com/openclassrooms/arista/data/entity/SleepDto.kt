package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep")
data class SleepDto(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,


    @ColumnInfo(name = "debut")
    var startTime: Long,

    @ColumnInfo(name = "duree")
    var duration : Int,

    @ColumnInfo(name = "qualite")
    var quality : Int

)
/**class LocalDateTimeConverter {
    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.toString()
    }
}**/