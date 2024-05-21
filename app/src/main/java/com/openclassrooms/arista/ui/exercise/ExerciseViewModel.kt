package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase,

) : ViewModel() {
    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    private val _exerciseFk = MutableStateFlow<Long?>(null)
    val exerciseFk: StateFlow<Long?> = _exerciseFk.asStateFlow()

 /**   init {
        loadAllExercises(userId)
    }
**/
    fun deleteExercise(exercise: Exercise, userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {

            deleteExerciseUseCase.execute(exercise)
            loadAllExercises(userId)

        }

    }

    private fun loadAllExercises(userId: Long) {
        viewModelScope.launch (Dispatchers.IO) {
            val exercises = getAllExercisesUseCase.execute(userId)
            _exercisesFlow.value = exercises

        }


    }

    fun addNewExercise(exercise: Exercise,userId: Long) {
        viewModelScope.launch (Dispatchers.IO) {

            addNewExerciseUseCase.execute(exercise)
            loadAllExercises(userId)
        }

    }


    
}
