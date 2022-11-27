package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.InvalidAnswerException
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.repository.QuestionAppRepository

class InsertAnswer(
    private val repository: QuestionAppRepository
) {
    @Throws(InvalidAnswerException::class)
    suspend operator fun invoke(answer: QuestionAppAnswer) {
        if(answer.userName.isBlank()){
            throw InvalidAnswerException("The answer can't be empty.")
        }
        repository.insertAnswer(answer)
    }
}