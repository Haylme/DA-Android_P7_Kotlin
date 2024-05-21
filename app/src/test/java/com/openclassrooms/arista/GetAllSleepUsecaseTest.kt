package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime


@RunWith(JUnit4::class)
class GetAllSleepUsecaseTest {

    @Mock
    private lateinit var sleepRepository: SleepRepository
    private lateinit var getAllSleepUsecase: GetAllSleepsUseCase


    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        getAllSleepUsecase = GetAllSleepsUseCase(sleepRepository)
    }

    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()

    }

    @Test
    fun `fetch all sleep data`(): Unit = runBlocking {


        val fakeSleep = listOf(
            Sleep(
                startTime = LocalDateTime.now(),
                duration = 30,
                quality = 9,
                userId = 1
            )
        )

        Mockito.`when`(getAllSleepUsecase.execute(1)).thenReturn(fakeSleep)

        val result = getAllSleepUsecase.execute(1)

        assertThat(result).isEqualTo(fakeSleep)

    }


}