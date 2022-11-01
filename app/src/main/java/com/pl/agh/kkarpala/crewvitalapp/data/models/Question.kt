package com.pl.agh.kkarpala.crewvitalapp.data.models

data class Question (
    val id: Int,
    val question: String,
    val answers: List<String>
)