package com.openclassrooms.arista.data.repository

import android.content.Context
import android.widget.Toast
import com.openclassrooms.arista.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao : UserDtoDao) {

    private val _userError = MutableStateFlow<String?>(null)
    private val userError: StateFlow<String?> = _userError


    suspend fun getUserById(id: Long): User? {
        return try {
           val user = userDao.getUserById(id)
                .map { userDto -> User.fromDto(userDto) }
                .firstOrNull() // Use firstOrNull to avoid throwing an exception if no elements are emitted

            if (user == null) {
                _userError.value = "User with ID $id not found"
            }
            user
        } catch (e: Exception) {
            _userError.value = "Error, please try again"
            null // Ensure null is returned in case of any Exception
        }
    }

    suspend fun addUser(user:User){

        try {
            userDao.insertUser(user.toDto())

        }catch (e:Exception){

            _userError.value = "Error, please try again"
        }



    }




}