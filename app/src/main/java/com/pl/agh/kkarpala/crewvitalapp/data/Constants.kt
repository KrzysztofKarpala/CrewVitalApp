package com.pl.agh.kkarpala.crewvitalapp.data

import com.pl.agh.kkarpala.crewvitalapp.data.models.Question

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
            "What is your favourite number?",
            listOf("1",
                "3",
                "7",
                "9")
        )
        questionsList.add(question2)
        val question3 = Question(
            3,
            "What is the weather today?",
            listOf("Cloudy",
                "Foggy",
                "Rainy",
                "Sunny")
        )
        questionsList.add(question3)
        val question4 = Question(
            4,
            "What is your favourite color?",
            listOf("Blue",
                "Green",
                "Red",
                "Yellow")
        )
        questionsList.add(question4)
        return questionsList
    }
}