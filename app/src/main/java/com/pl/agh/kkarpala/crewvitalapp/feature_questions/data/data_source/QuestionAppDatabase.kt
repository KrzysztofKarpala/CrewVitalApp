package com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer


@Database(
    entities = [QuestionAppAnswer::class],
    version = 1
)
abstract class QuestionAppDatabase : RoomDatabase() {

    abstract val questionAppDao: QuestionAppDatabaseDao

    companion object {

        private var INSTANCE: QuestionAppDatabase? = null

        fun getInstance(context: Context): QuestionAppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionAppDatabase::class.java,
                        "question_app_db"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
        const val DATABASE_NAME = "question_app_db"
    }
}
