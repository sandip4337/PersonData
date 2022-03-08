package com.sandip.persondata.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sandip.persondata.LocalDatabase.PersonDatabase
import com.sandip.persondata.Reository.Repository
import com.sandip.persondata.data.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {

    val personInfo: LiveData<List<Person>>
    private val repository: Repository

    init {
        val personDao = PersonDatabase.getDataBase(application).personDao()
        repository = Repository(personDao)
        personInfo = repository.personInfo
    }

    fun addUser(info : Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(info)
        }
    }

    fun updateUser(info: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(info)
        }
    }

    fun deleteUser(info: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(info)
        }
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Person>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }


}