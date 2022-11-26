package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.InvalidAnswerException
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
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

    private val _answerOpen = mutableStateOf(OpenQuestionPageState(
        hint = "Enter your answer"
    ))
    val answerOpen: State<OpenQuestionPageState> = _answerOpen

    private val _eventFlow = MutableSharedFlow<UiEventOpen>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentAnswerId: Int? = null

    init{
        savedStateHandle.get<Int>("answerId")?.let{ answerId ->
            if(answerId != -1) {
                viewModelScope.launch {
                    questionAppUseCases.getAnswer(answerId)?.also{ answer ->
                        currentAnswerId = answer.answerId
                        _answerOpen.value = answerOpen.value.copy(
                            answer = answer.question_5,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEventOpen(event: OpenQuestionPageEvent){
        when(event){
            is OpenQuestionPageEvent.EnteredAnswer -> {
                _answerOpen.value = answerOpen.value.copy(
                    answer = event.value
                )
            }
            is OpenQuestionPageEvent.ChangedAnswerFocus -> {
                _answerOpen.value = answerOpen.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            answerOpen.value.answer.isBlank()
                )
            }
            is OpenQuestionPageEvent.SaveOpenAnswer -> {
                viewModelScope.launch {
                    try {
                        questionAppUseCases.insertAnswer(
                            QuestionAppAnswer(
                                userName = "",
                                question_1 = "",
                                question_2 = "",
                                question_3 = "",
                                question_4 = "",
                                question_5 = answerOpen.value.answer,
                                question_6 = "",
                                question_7 = "",
                                question_8 = "",
                                answerId = currentAnswerId
                            )
                        )
                        _eventFlow.emit(UiEventOpen.SaveOpenAnswer)
                    } catch(ex: InvalidAnswerException){
                        _eventFlow.emit(
                            UiEventOpen.ShowToast(
                                message = ex.message ?: "Empty answer"
                            )
                        )
                    }
                }
            }
        }
    }
}

sealed class UiEventOpen{
    object SaveOpenAnswer: UiEventOpen()
    data class ShowToast(val message: String): UiEventOpen()
}