package com.openclassrooms.arista.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.SleepDto
import kotlinx.coroutines.flow.Flow


@Dao
interface SleepDtoDao {


    @Insert
    suspend fun insertSleep(sleep: SleepDto):Long

    @Query("SELECT * FROM sleep WHERE user_id = :userId")
    fun getAllSleep(userId: Long): Flow<List<SleepDto>>

    @Query("DELETE FROM sleep WHERE user_id = :userId")
    fun deleteSleepById (userId:Long)



}