package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page

import com.pl.agh.kkarpala.crewvitalapp.core.questions.OpenConstants
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.OpenQuestion
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer

data class OpenQuestionPageState(
    val questionAnswer : QuestionAppAnswer = QuestionAppAnswer(0,"","","","","", "","","",""),
    val openQuestionList: List<OpenQuestion> = OpenConstants.getQuestions(),
    val questionId: Int = 1,
    val answer: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)