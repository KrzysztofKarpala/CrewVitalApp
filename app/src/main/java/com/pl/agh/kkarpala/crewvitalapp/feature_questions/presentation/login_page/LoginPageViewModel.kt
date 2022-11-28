package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.InvalidAnswerException
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.QuestionAppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val questionAppUseCases: QuestionAppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _name = mutableStateOf(LoginPageState(
        hint = "Enter your name"
    ))
    val name: State<LoginPageState> = _name

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentAnswerId: Int? = null


    init{
        savedStateHandle.get<Int>("answerId")?.let{ answerId ->
            if(answerId != -1) {
                viewModelScope.launch {
                    questionAppUseCases.getAnswer(answerId)?.also{ answer ->
                        currentAnswerId = answer.answerId
                        _name.value =  name.value.copy(
                            name = answer.userName,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }


    fun onEventLogin(event: LoginPageEvent){
        when(event){
            is LoginPageEvent.EnteredName -> {
                _name.value = name.value.copy(
                    name = event.value
                )
            }
            is LoginPageEvent.ChangeNameFocus -> {
                _name.value = name.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            name.value.name.isBlank()
                )
            }
            is LoginPageEvent.SaveName -> {
                viewModelScope.launch {
                    try{
                        questionAppUseCases.insertAnswer(
                            QuestionAppAnswer(
                                userName = name.value.name,
                                question_1 = "",
                                question_2 = "",
                                question_3 = "",
                                question_4 = "",
                                question_5 = "",
                                question_6 = "",
                                question_7 = "",
                                question_8 = "",
                                answerId = 1
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveName)
                    } catch(ex: InvalidAnswerException){
                        _eventFlow.emit(
                            UiEvent.ShowToast(
                                message = ex.message ?: "Empty name"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent{
        object SaveName: UiEvent()
        data class ShowToast(val message: String): UiEvent()
    }
}