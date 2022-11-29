package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables.LoginPage
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables.OpenQuestionPage
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables.QuestionPage
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables.ResultPage
import com.pl.agh.kkarpala.crewvitalapp.utils.Screen
import com.pl.agh.kkarpala.crewvitalapp.ui.theme.CrewVitalAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            CrewVitalAppTheme {
                val navController = rememberNavController()
                val sharedDataManager = SharedDataManager()
                NavHost(navController = navController, startDestination = Screen.LoginPage.route){
                    composable(Screen.LoginPage.route){
                        LoginPage(navController = navController, sharedDataManager = sharedDataManager)
                    }
                    composable(
                        Screen.QuestionPage.route + "/{questionId}",
                        arguments = listOf(
                            navArgument("questionId"){
                                type = NavType.IntType
                            })) { entry ->
                        QuestionPage(navController = navController, questionId = entry.arguments!!.getInt("questionId"), sharedDataManager = sharedDataManager)
                    }
                    composable(
                        Screen.OpenQuestionPage.route+"/{questionId}",
                        arguments = listOf(
                            navArgument("questionId"){
                                type = NavType.IntType
                            })){ entry ->
                        OpenQuestionPage(navController = navController, questionId = entry.arguments!!.getInt("questionId"), sharedDataManager = sharedDataManager)
                    }
                    composable(Screen.ResultPage.route){
                        ResultPage(navController = navController, sharedDataManager = sharedDataManager)
                    }
                }
            }
        }
    }
}

