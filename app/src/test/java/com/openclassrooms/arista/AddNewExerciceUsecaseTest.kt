package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.time.LocalDateTime


@RunWith(JUnit4::class)
class AddNewExerciceUsecaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase


    @Before
    fun setUp() {
        // Initialize the mock repository
        MockitoAnnotations.openMocks(this)

        // Initialize the use case with the mock repository
        addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
    }

    @Test
    fun `should add new exercise successfully`() = runBlocking {
        // Create a fake exercise
        val fakeExercise = Exercise(
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5,
            userId = 4

        )

        // Execute the use case with the fake exercise
        addNewExerciseUseCase.execute(fakeExercise)

        // Verify that the repository's addExercise method was called with the correct exercise
        verify(exerciseRepository).addExercise(fakeExercise)
    }
}