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

    @Query("SELECT * FROM user WHERE userId = :id")
    fun getUserById (id: Long): Flow<UserDto>

    @Query("DELETE FROM user WHERE userId = :id")
    suspend fun deleteUserById (id:Long)

}