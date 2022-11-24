package com.pl.agh.kkarpala.crewvitalapp.infrastructure

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionAppEntity::class], version = 1, exportSchema = false)
abstract class QuestionAppDatabase : RoomDatabase() {
    abstract fun questionAppDao(): QuestionAppDatabaseDao

    companion object {

        private var INSTANCE: QuestionAppDatabase? = null

        fun getInstance(context: Context): QuestionAppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionAppDatabase::class.java,
                        "answers_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}