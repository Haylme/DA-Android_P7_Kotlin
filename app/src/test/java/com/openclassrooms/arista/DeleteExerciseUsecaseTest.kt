package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.time.LocalDateTime


@RunWith(JUnit4::class)
class DeleteExerciseUsecaseTest {

    @Mock
    lateinit var exerciseRepository: ExerciseRepository
    lateinit var deleteExerciseUseCase: DeleteExerciseUseCase


  @Before
  fun setup() {

      MockitoAnnotations.initMocks(this)
      deleteExerciseUseCase = DeleteExerciseUseCase(exerciseRepository)



  }


    @After
    fun teardown() {

        Mockito.framework().clearInlineMocks()

    }

    @Test
    fun ` delete data from database`() = runBlocking{

        val data =    Exercise (

            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Football,
            intensity = 8


        )



        deleteExerciseUseCase.execute(data)

        verify (exerciseRepository).deleteExercise(data)


    }




}