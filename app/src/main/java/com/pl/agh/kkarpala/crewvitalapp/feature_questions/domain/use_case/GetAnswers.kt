package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.repository.QuestionAppRepository
import kotlinx.coroutines.flow.Flow

class GetAnswers(
    private val repository: QuestionAppRepository
) {
    operator fun invoke(): Flow<List<QuestionAppAnswer?>> {
        return repository.getAnswers()
    }
}