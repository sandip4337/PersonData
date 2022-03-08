package com.sandip.persondata.LocalDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sandip.persondata.data.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

//    this parameter means if there is a new user same as existing user in this case just ignore it
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(info : Person)

    @Update
    suspend fun updatePerson(info: Person)

    @Delete
    suspend fun deletePerson(info: Person)

    @Query("DELETE FROM persontable")
    suspend fun deleteAllPerson()

    @Query("SELECT * FROM PersonTable ORDER BY Id ASC")
    fun getInfo(): LiveData<List<Person>>

    @Query("SELECT * FROM PersonTable WHERE FirstName LIKE :searchQuery OR LastName LIKE :searchQuery")
    fun searchDatabase(searchQuery:String): Flow<List<Person>>

}