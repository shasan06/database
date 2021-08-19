package com.example.archelocapp_1.source.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sample")
data class SampleTable(
    val name: String,
    var id: Int? = null
)