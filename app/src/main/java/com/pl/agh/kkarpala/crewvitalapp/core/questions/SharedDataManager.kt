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
        questionAppAnswer.userName = answer
    }
    fun addAnswerToQuestionAppAnswer(answer: String, questionId: Int){
        if(questionId == 1){
            questionAppAnswer.question_1 = answer
        }
        else if(questionId == 2){
            questionAppAnswer.question_2 = answer
        }
        else if(questionId == 3){
            questionAppAnswer.question_3 = answer
        }
        else if(questionId == 4){
            questionAppAnswer.question_4 = answer
        }
        else if(questionId == 5){
            questionAppAnswer.question_5 = answer
        }
        else if(questionId == 6){
            questionAppAnswer.question_6 = answer
        }
        else if(questionId == 7){
            questionAppAnswer.question_7 = answer
        }
        else if(questionId == 8){
            questionAppAnswer.question_8 = answer
        }
    }
}