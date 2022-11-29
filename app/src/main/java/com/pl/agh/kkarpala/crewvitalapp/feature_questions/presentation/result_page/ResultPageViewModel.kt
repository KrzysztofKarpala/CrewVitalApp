package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.result_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.InvalidAnswerException
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.QuestionAppUseCases
import com.pl.agh.kkarpala.crewvitalapp.utils.HttpRetriver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultPageViewModel @Inject constructor(
    private val questionAppUseCases: QuestionAppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    fun setPageState(result : ResultPageState){
        _answer.value = result
    }

    private val httpRetriver = HttpRetriver()

    var _answer = mutableStateOf(ResultPageState())
    var answer: State<ResultPageState> = _answer

    private val _eventFlow = MutableSharedFlow<UiEventResult>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEventResult(event: ResultPageEvent){
        when(event){
            is ResultPageEvent.SaveAnswer -> {
                viewModelScope.launch {
                   questionAppUseCases.insertAnswer(
                        QuestionAppAnswer(
                            userName = _answer.value.questionAnswer.userName,
                            question_1 = _answer.value.questionAnswer.question_1,
                            question_2 = _answer.value.questionAnswer.question_2,
                            question_3 = _answer.value.questionAnswer.question_3,
                            question_4 = _answer.value.questionAnswer.question_4,
                            question_5 = _answer.value.questionAnswer.question_5,
                            question_6 = _answer.value.questionAnswer.question_6,
                            question_7 = _answer.value.questionAnswer.question_7,
                            question_8 = _answer.value.questionAnswer.question_8,
                            answerId = _answer.value.questionAnswer.answerId
                        )
                    )
/*                    httpRetriver.sendData(
                        QuestionAppAnswer(
                            userName = _answer.value.questionAnswer.userName,
                            question_1 = _answer.value.questionAnswer.question_1,
                            question_2 = _answer.value.questionAnswer.question_2,
                            question_3 = _answer.value.questionAnswer.question_3,
                            question_4 = _answer.value.questionAnswer.question_4,
                            question_5 = _answer.value.questionAnswer.question_5,
                            question_6 = _answer.value.questionAnswer.question_6,
                            question_7 = _answer.value.questionAnswer.question_7,
                            question_8 = _answer.value.questionAnswer.question_8,
                            answerId = _answer.value.questionAnswer.answerId
                        )
                    )*/
                    _eventFlow.emit(UiEventResult.SaveAnswer)

                }
            }
        }
    }

    sealed class UiEventResult{
        object SaveAnswer: UiEventResult()
    }
}