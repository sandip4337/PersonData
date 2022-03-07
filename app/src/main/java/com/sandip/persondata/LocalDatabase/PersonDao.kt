package com.sandip.persondata.LocalDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sandip.persondata.data.Person

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

}