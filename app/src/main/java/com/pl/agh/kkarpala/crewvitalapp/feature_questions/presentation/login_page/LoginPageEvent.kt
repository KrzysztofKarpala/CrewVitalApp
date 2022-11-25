package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page

import androidx.compose.ui.focus.FocusState
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer

sealed class LoginPageEvent{
    data class EnteredName(val value: String): LoginPageEvent()
    data class ChangeNameFocus(val focusState: FocusState): LoginPageEvent()
    object SaveName: LoginPageEvent()
}
