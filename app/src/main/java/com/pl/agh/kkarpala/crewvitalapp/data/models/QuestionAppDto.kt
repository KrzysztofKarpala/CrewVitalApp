package com.pl.agh.kkarpala.crewvitalapp.data.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class QuestionAppDto(

        var userName: String,

        var question_1: String,

        var question_2: String,

        var question_3: String,

        var question_4: String,

        var question_5: Int,

        var question_6: Int,

        var question_7: Int,

        var question_8: Int,
)