package com.pl.agh.kkarpala.crewvitalapp.di

import android.app.Application
import androidx.room.Room
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.data_source.QuestionAppDatabase
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.data.repository.QuestionAppRepositoryImpl
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.repository.QuestionAppRepository
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.DeleteAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.GetAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.InsertAnswer
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.domain.use_case.QuestionAppUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuestionAppDatabase(app: Application): QuestionAppDatabase{
        return Room.databaseBuilder(
            app,
            QuestionAppDatabase:: class.java,
            QuestionAppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuestionAppRepository(db: QuestionAppDatabase): QuestionAppRepository{
        return QuestionAppRepositoryImpl(db.questionAppDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: QuestionAppRepository): QuestionAppUseCases{
        return QuestionAppUseCases(
            deleteAnswer = DeleteAnswer(repository),
            insertAnswer = InsertAnswer(repository),
            getAnswer = GetAnswer(repository)
        )
    }
}