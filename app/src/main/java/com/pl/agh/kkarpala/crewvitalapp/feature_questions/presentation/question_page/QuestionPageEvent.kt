package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.question_page

import androidx.compose.ui.focus.FocusState

sealed class QuestionPageEvent {
    data class EnteredAnswer(val value: String): QuestionPageEvent()
    data class ChangedAnswerFocus(val focusState: FocusState): QuestionPageEvent()
    object SaveOpenAnswer: QuestionPageEvent()
}