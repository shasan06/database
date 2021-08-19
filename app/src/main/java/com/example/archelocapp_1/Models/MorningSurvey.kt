package com.example.archelocapp_1.Models
//data class for morning survey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Morning_survey_table")
data class MorningSurvey(

    @ColumnInfo(name = "area") var area: String = "",

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "beach")
    var beach: String = "",

    @ColumnInfo(name = "sector")
    var sector: String = "",

    @ColumnInfo(name = "subsector")
    var subsector: String = "",

    @ColumnInfo(name = "emergence_event")
    var emergence_event: String = "",

    @ColumnInfo(name = "nest")
    var nest: String = "-1",

    @ColumnInfo(name = "distance_to_sea")
    var distance_to_sea: String = "-1",

    @ColumnInfo(name = "track_type")
    var track_type: String = "-1",

    @ColumnInfo(name = "non_nesting_attempts")
    var non_nesting_attempts: String = "-1",

    @ColumnInfo(name = "gps_latitude")
    var gps_latitude: String = "-1",

    @ColumnInfo(name = "gps_longitude")
    var gps_longitude: String = "-1",

    @ColumnInfo(name = "tags")
    var tags: String = " ",

    @ColumnInfo(name = "Tests")
    var comments: String = "Test",

    @ColumnInfo(name = "myphoto")
    var photo_id: String = "myphoto",

    @PrimaryKey(autoGenerate = true)
    var nest_id : Long = 0L
)