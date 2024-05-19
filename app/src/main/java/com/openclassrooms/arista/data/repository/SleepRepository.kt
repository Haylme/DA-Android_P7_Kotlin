package com.openclassrooms.arista.data.repository

import android.content.Context
import android.widget.Toast
import com.openclassrooms.arista.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class SleepRepository(private val sleepDao: SleepDtoDao) {


    private val _sleepError = MutableStateFlow<String?>(null)
    private val sleepError: StateFlow<String?> = _sleepError

    // Get all sleep records


    suspend fun getAllSleep(userId: Long): List<Sleep> {


        return try {
            val sleepList = sleepDao.getAllSleep(userId)
                .first()
                .map { Sleep.fromDto(it) }

            if (sleepList.isEmpty()) {
                _sleepError.value = "No sleep records found for this user"
            }

            sleepList


        } catch (e: Exception) {

            _sleepError.value = "No sleep records found for this user"
            emptyList()
        }


    }


    suspend fun addSleep(sleep: Sleep) {

        try {

            sleepDao.insertSleep(sleep.toDto())

        } catch (e: Exception) {
            _sleepError.value = "No sleep records found for this user"

        }


    }


    suspend fun deleteSleep(sleep: Sleep) {

        try {

            sleep.id?.let {
                sleepDao.deleteSleepById(userId = sleep.userId)

            } ?: throw IllegalArgumentException("Sleep id cannot be null")

        } catch (e: Exception) {
            _sleepError.value = "No sleep records found for this user"



        } catch (e: Exception) {
            _sleepError.value = "No sleep records found for this user"
        }
    }




}