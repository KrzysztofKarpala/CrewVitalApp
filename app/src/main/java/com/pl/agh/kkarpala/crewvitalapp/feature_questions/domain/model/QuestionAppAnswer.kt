package com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionAppAnswer (
    @PrimaryKey var answerId: Int? = null,

    var userName: String,

    var question_1: String,

    var question_2: String,

    var question_3: String,

    var question_4: String,

    var question_5: String,

    var question_6: String,

    var question_7: String,

    var question_8: String,
)

class InvalidAnswerException(message: String): Exception(message)