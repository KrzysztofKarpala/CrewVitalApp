package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page

import androidx.compose.ui.focus.FocusState

sealed class OpenQuestionPageEvent {
    data class EnteredAnswer(val value: String): OpenQuestionPageEvent()
    data class ChangedAnswerFocus(val focusState: FocusState): OpenQuestionPageEvent()
    object SaveOpenAnswer: OpenQuestionPageEvent()
}