package com.pl.agh.kkarpala.crewvitalapp.infrastructure

import androidx.lifecycle.LiveData

class QuestionAppRepository(private val questionAppDatabaseDao: QuestionAppDatabaseDao) {

    val readAllData: LiveData<List<QuestionAppEntity>> = questionAppDatabaseDao.getAll()

    suspend fun addTodo(todoItem: QuestionAppEntity) {
        questionAppDatabaseDao.insert(todoItem)
    }

    suspend fun updateTodo(todoItem: QuestionAppEntity) {
        questionAppDatabaseDao.update(todoItem)
    }

    suspend fun deleteTodo(todoItem: QuestionAppEntity) {
        questionAppDatabaseDao.delete(todoItem)
    }

    suspend fun deleteAllTodos() {
        questionAppDatabaseDao.deleteAllTodos()
    }
}