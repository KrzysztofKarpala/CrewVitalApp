package com.pl.agh.kkarpala.crewvitalapp.utils

import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.model.QuestionAppAnswer
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpRetriver {
    private val service: HttpService

    companion object{
        const val BASE_URL = "TO DO"
    }

    init{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(HttpService::class.java)

    }

    suspend fun sendData(data: QuestionAppAnswer): Response<QuestionAppAnswer> {
        return service.sendResults(data)
    }
}
