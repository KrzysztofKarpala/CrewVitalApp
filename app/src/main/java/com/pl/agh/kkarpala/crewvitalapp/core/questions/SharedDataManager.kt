package com.pl.agh.kkarpala.crewvitalapp.core.questions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer

class SharedDataManager {
    var questionAppAnswer by mutableStateOf(QuestionAppAnswerDto(0,"","", "","","","","","",""))
        set
/*    var questionAppAnswer by mutableStateOf<QuestionAppAnswer?>(null)
        set*/

    fun addIdToQuestionAppAnswer(answer: Int){
        questionAppAnswer.answerId= answer
    }
    fun addNameToQuestionAppAnswer(answer: String){
        questionAppAnswer.username = answer
    }
    fun addAnswerToQuestionAppAnswer(answer: String, questionId: Int){
        if(questionId == 1){
            questionAppAnswer.questionAnswer_1 = answer
        }
        else if(questionId == 2){
            questionAppAnswer.questionAnswer_2 = answer
        }
        else if(questionId == 3){
            questionAppAnswer.questionAnswer_3 = answer
        }
        else if(questionId == 4){
            questionAppAnswer.questionAnswer_4 = answer
        }
        else if(questionId == 5){
            questionAppAnswer.questionAnswer_5 = answer
        }
        else if(questionId == 6){
            questionAppAnswer.questionAnswer_6 = answer
        }
        else if(questionId == 7){
            questionAppAnswer.questionAnswer_7 = answer
        }
        else if(questionId == 8){
            questionAppAnswer.questionAnswer_8 = answer
        }
    }
}