package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.repository.QuestionAppRepository

class DeleteAnswer(
    private val repository: QuestionAppRepository
) {
    suspend operator fun invoke(answer: QuestionAppAnswer){
        repository.deleteAnswer(answer)
    }
}