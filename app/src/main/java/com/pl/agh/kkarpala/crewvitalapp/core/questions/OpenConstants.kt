package com.pl.agh.kkarpala.crewvitalapp.core.questions

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.OpenQuestion

object OpenConstants {
    const val USER_NAME: String = "user_name"

    fun getQuestions(): ArrayList<OpenQuestion>{
        val questionsList = ArrayList<OpenQuestion>()
        val question1 = OpenQuestion(
            1,
            "What is your body temperature (°C)?"
        )
        questionsList.add(question1)
        val question2 = OpenQuestion(
            2,
            "How much water have you drunk (approx ml)?"
        )
        questionsList.add(question2)
        val question3 = OpenQuestion(
            3,
            "What is your pulse?"
        )
        questionsList.add(question3)
        val question4 = OpenQuestion(
            4,
            "How much do you weight?"
        )
        questionsList.add(question4)
        return questionsList
    }
}