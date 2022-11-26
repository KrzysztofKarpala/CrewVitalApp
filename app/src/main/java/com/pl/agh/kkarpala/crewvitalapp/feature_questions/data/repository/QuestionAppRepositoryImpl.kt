package com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.repository

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.data_source.QuestionAppDatabaseDao
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.repository.QuestionAppRepository
import kotlinx.coroutines.flow.Flow

class QuestionAppRepositoryImpl (
    private val dao: QuestionAppDatabaseDao
        ) : QuestionAppRepository {

    override fun getAnswers(): Flow<List<QuestionAppAnswer>> {
        return dao.getAllAnswers()
    }

    override suspend fun getAnswerById(id: Int): QuestionAppAnswer? {
        return dao.getAnswerById(id)
    }

    override suspend fun insertAnswer(answer: QuestionAppAnswer) {
        dao.insert(answer)
    }

    override suspend fun deleteAnswer(answer: QuestionAppAnswer) {
        dao.delete(answer)
    }
}