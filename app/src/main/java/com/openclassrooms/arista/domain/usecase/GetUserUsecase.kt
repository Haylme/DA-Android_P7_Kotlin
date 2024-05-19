package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(id:Long): User? {
        return userRepository.getUserById(id)
    }
}