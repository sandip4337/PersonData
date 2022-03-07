package com.sandip.persondata.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sandip.persondata.data.Person

@Database(entities = [Person::class],version = 1,exportSchema = false)
abstract class PersonDatabase: RoomDatabase() {

    abstract fun personDao() : PersonDao

    companion object{
        @Volatile
        private var INSTANCE : PersonDatabase? = null

        fun getDataBase(context: Context):PersonDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PersonDatabase::class.java,
                        "DataDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}