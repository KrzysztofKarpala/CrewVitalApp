package com.pl.agh.kkarpala.crewvitalapp.data.models

data class Question (
    val id: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String
)