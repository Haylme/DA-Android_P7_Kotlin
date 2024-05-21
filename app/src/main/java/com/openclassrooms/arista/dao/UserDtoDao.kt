package com.openclassrooms.arista.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDtoDao {

    @Insert
    suspend fun insertUser(user : UserDto):Long

    @Query("SELECT * FROM user WHERE userById = :id")
    fun getUserById (id: Long): Flow<UserDto>

    @Query("DELETE FROM user WHERE userById = :id")
    suspend fun deleteUserById (id:Long)

}