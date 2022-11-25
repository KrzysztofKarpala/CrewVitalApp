package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.repository

import androidx.lifecycle.LiveData
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.data_source.QuestionAppDatabaseDao
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import kotlinx.coroutines.flow.Flow


interface QuestionAppRepository {

    fun getAnswers(): Flow<List<QuestionAppAnswer>>

    suspend fun getAnswerById(id: Int) : QuestionAppAnswer?

    suspend fun insertAnswer(answer: QuestionAppAnswer)

    suspend fun deleteAnswer(answer: QuestionAppAnswer)
}
