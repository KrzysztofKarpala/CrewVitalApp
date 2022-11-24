package com.pl.agh.kkarpala.crewvitalapp.infrastructure

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionAppDatabaseDao {
    @Query("SELECT * from answers_list")
    fun getAll(): LiveData<List<QuestionAppEntity>>

    @Query("SELECT * from answers_list where itemId = :id")
    fun getById(id: Int): QuestionAppEntity?

    @Insert
    suspend fun insert(item: QuestionAppEntity)

    @Update
    suspend fun update(item: QuestionAppEntity)

    @Delete
    suspend fun delete(item: QuestionAppEntity)

    @Query("DELETE FROM answers_list")
    suspend fun deleteAllTodos()
}