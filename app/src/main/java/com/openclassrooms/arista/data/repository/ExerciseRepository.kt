package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

class ExerciseRepository(private val exerciseDao: ExerciseDtoDao) {


    // Get all exercises
    suspend fun getAllExercises(userId: Long): List<Exercise> {
        return exerciseDao.getAllExercises(userId)
            .first() // Collect the first emission of the Flow
            .map { Exercise.fromDto(it) } // Convert every DTO in Exercise
    }


    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise.toDto())
    }


    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel
        exercise.id?.let {
            exerciseDao.deleteExerciseById(
                userId = exercise.userId,
            )
        }
    }
}