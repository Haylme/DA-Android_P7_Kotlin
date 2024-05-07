package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

class SleepRepository(private val sleepDao: SleepDtoDao) {

    // Get all sleep records


    suspend fun getAllSleep(): List<Sleep> {

        return sleepDao.getAllSleep()
            .first()
            .map { Sleep.fromDto(it) }

    }


    suspend fun addSleep(sleep: Sleep) {

        sleepDao.insertSleep(sleep.toDto())

    }



    suspend fun deleteSleep(sleep: Sleep){

        sleep.id?.let {
            sleepDao.deleteSleepById(id = it)
        }

    }


}