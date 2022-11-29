package com.pl.agh.kkarpala.crewvitalapp.core.questions

import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.Question

object Constants {
    const val USER_NAME: String = "user_name"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val question1 = Question(
            1,
            "How are you feeling today?",
            listOf("Very good!",
            "Good",
            "Average",
            "Bad")
        )
        questionsList.add(question1)
        val question2 = Question(
            2,
            "What was your sleep quality?",
            listOf("Very good!",
                "Good",
                "Average",
                "Bad")
        )
        questionsList.add(question2)
        val question3 = Question(
            3,
            "How high is your stress level?",
            listOf("Very high",
                "High",
                "Moderate",
                "Low")
        )
        questionsList.add(question3)
        val question4 = Question(
            4,
            "Are you in time with your tasks?",
            listOf("Yes",
                "Almost",
                "No",
                "Hard to estimate")
        )
        questionsList.add(question4)
        return questionsList
    }
}