package com.pl.agh.kkarpala.crewvitalapp.utils

import android.telecom.Call
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface HttpService {
    @Headers("Content-Type: application/json")
    @POST("answers")
    suspend fun sendResults(@Body body: QuestionAppAnswer): Response<QuestionAppAnswer>
}