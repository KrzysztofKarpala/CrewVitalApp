package com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.data_source

import androidx.room.*
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionAppDatabaseDao {

    @Query("SELECT * FROM QuestionAppAnswer")
    fun getAllAnswers(): Flow<List<QuestionAppAnswer>>

    @Query("SELECT * from QuestionAppAnswer where answerId = :id")
    fun getAnswerById(id: Int): QuestionAppAnswer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(answer: QuestionAppAnswer)

    @Delete
    suspend fun delete(answer: QuestionAppAnswer)

}