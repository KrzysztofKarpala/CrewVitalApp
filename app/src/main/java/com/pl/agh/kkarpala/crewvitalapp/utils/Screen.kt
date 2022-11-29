package com.pl.agh.kkarpala.crewvitalapp.utils

sealed class Screen(val route: String) {
    object LoginPage : Screen("login_page")
    object ResultPage : Screen("result_page")
    object QuestionPage : Screen("question_page")
    object OpenQuestionPage : Screen("open_question_page")

    fun withArgs(vararg args: Int): String{
        return buildString{
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }
}