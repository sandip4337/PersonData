package com.sandip.persondata.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "PersonTable")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val Id : Int,
    val FirstName : String,
    val LastName : String,
    val Age : Int
): Parcelable
