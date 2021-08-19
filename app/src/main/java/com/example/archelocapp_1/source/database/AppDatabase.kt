package com.example.archelocapp_1.source.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class AppDatabase : RoomDatabase() {


    companion object {
        private var appDatabase: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return appDatabase!!;
        }

    }


}