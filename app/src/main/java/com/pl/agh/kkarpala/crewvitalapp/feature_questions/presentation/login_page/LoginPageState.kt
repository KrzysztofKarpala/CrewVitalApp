package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer

data class LoginPageState(
    val questionAnswer : QuestionAppAnswer = QuestionAppAnswer(0,"","","","","", "","","",""),
    val name: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
