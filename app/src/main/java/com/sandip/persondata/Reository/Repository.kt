package com.sandip.persondata.Reository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sandip.persondata.LocalDatabase.PersonDao
import com.sandip.persondata.LocalDatabase.PersonDatabase
import com.sandip.persondata.data.Person
import kotlinx.coroutines.flow.Flow

class Repository(private val personDao: PersonDao) {


    val personInfo: LiveData<List<Person>> = personDao.getInfo()

    suspend fun addUser(info : Person){
        personDao.addUser(info)
    }

    suspend fun updateUser(info : Person){
        personDao.updatePerson(info)
    }

    suspend fun deleteUser(info: Person){
        personDao.deletePerson(info)
    }

    suspend fun deleteAllUser(){
        personDao.deleteAllPerson()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Person>> {
        return personDao.searchDatabase(searchQuery)
    }

}