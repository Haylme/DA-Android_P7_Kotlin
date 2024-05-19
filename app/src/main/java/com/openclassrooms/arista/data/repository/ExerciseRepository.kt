package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class ExerciseRepository(private val exerciseDao: ExerciseDtoDao) {

    private val _exerciseError = MutableStateFlow<String?>(null)
    private val exerciseError: StateFlow<String?> = _exerciseError // Public getter to avoid setting the value of _getExerciseStateFlow




    // Get all exercises
    suspend fun getAllExercises(userId: Long): List<Exercise> {
        return try {
            val exercises = exerciseDao.getAllExercises(userId)
                .first() // Collect the first emission of the Flow
                .map { Exercise.fromDto(it) } // Convert every DTO into Exercise

            if (exercises.isEmpty()) {
                _exerciseError.value = "No exercises found"
            }
            exercises
        } catch (e: Exception) {
            _exerciseError.value = "Error, please try again"
            emptyList()
        }
    }


    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) {
        try {
            exerciseDao.insertExercise(exercise.toDto())

        } catch (e: Exception) {
           _exerciseError.value = "Error, please try again"


        }
    }

    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) {
        try {
            // If there is no id, you can raise an exception and catch it in the use case and viewmodel
            exercise.id?.let {
                exerciseDao.deleteExerciseById(
                    userId = exercise.userId,
                )
            } ?: throw IllegalArgumentException("Exercise ID is null")
        } catch (e: Exception) {
            _exerciseError.value = "Error, please try again"
        }
    }



}

