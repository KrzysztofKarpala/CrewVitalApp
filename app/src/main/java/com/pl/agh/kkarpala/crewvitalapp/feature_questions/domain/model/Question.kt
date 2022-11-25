package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model

data class Question (
    val id: Int,
    val question: String,
    val answers: List<String>
)