package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao : UserDtoDao) {


    suspend fun getUserById(id: Long): User {
        return userDao.getUserById(id)
            .map { userDto -> User.fromDto(userDto) } // Convert each UserDto to User
            .first() // Get  only User emitted by the flow
    }

    suspend fun addUser(user:User){

        userDao.insertUser(user.toDto())

    }




}