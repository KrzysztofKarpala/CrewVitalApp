package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionAppAnswer (
    @PrimaryKey val answerId: Int? = null,

    val userName: String,

    val question_1: String,

    val question_2: String,

    val question_3: String,

    val question_4: String,

    val question_5: Int,

    val question_6: Int,

    val question_7: Int,

    val question_8: Int,
)

class InvalidAnswerException(message: String): Exception(message)