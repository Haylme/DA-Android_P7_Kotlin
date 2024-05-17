package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class GetUserUsecaseTest {


    @Mock

        private lateinit var userRepository: UserRepository
        private lateinit var getUserUsecase: GetUserUsecase


    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)

        getUserUsecase = GetUserUsecase(userRepository)


    }

    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()

    }

    @Test
    fun `should get user from repository`(): Unit = runBlocking {

        val userData = User (
            id = 1,
            name = "Scoubidou",
            email = "scoubi@exemple.com",
            password = "azerty"


        )
        // Set up the mock behavior
        Mockito.`when`(userRepository.getUserById(1)).thenReturn(userData)
        val result = getUserUsecase.execute(1)
        assertThat(result).isEqualTo(userData)

    }


}