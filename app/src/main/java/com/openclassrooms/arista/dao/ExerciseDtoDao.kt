package com.openclassrooms.arista.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.ExerciseDto
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDtoDao {


    @Insert
    suspend fun insertExercise(exercise: ExerciseDto): Long


    @Query("SELECT * FROM exercise WHERE user_id = :userId")
    fun getAllExercises(userId: Long): Flow<List<ExerciseDto>>


    @Query("DELETE FROM exercise WHERE user_id = :userId")
    suspend fun deleteExerciseById(userId: Long)



}