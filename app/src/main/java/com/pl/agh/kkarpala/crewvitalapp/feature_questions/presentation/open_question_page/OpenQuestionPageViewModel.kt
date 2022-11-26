package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.QuestionAppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenQuestionPageViewModel @Inject constructor(
    private val questionAppUseCases: QuestionAppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _answer = mutableStateOf(OpenQuestionPageState(
        answer = ""
    ))
    val answer: State<OpenQuestionPageState> = _answer

    private val _eventFlow = MutableSharedFlow<UiEventOpen>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentAnswerId: Int? = null

    init{
        savedStateHandle.get<Int>("answerId")?.let{ answerId ->
            if(answerId != -1) {
                viewModelScope.launch {
                    questionAppUseCases.getAnswer(answerId)?.also{ answer ->
                        currentAnswerId = answer.answerId
                    }
                }
            }
        }
    }
}

sealed class UiEventOpen{
    object SaveOpenAnswer: UiEventOpen()
    data class ShowSnackbar(val message: String): UiEventOpen()
}