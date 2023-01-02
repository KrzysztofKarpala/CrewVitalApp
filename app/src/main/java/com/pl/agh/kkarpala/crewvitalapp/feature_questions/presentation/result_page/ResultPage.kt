package com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.R
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.result_page.ResultPageEvent
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.result_page.ResultPageState
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.result_page.ResultPageViewModel
import com.pl.agh.kkarpala.crewvitalapp.utils.Screen
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ResultPage(navController: NavController,
               viewModel: ResultPageViewModel = hiltViewModel(),
               sharedDataManager: SharedDataManager){

    var result = ResultPageState()

    result.questionAnswer.answerId= sharedDataManager.questionAppAnswer.answerId
    result.questionAnswer.userName= sharedDataManager.questionAppAnswer.username
    result.questionAnswer.question_1= sharedDataManager.questionAppAnswer.questionAnswer_1
    result.questionAnswer.question_2= sharedDataManager.questionAppAnswer.questionAnswer_2
    result.questionAnswer.question_3= sharedDataManager.questionAppAnswer.questionAnswer_3
    result.questionAnswer.question_4= sharedDataManager.questionAppAnswer.questionAnswer_4
    result.questionAnswer.question_5= sharedDataManager.questionAppAnswer.questionAnswer_5
    result.questionAnswer.question_6= sharedDataManager.questionAppAnswer.questionAnswer_6
    result.questionAnswer.question_7= sharedDataManager.questionAppAnswer.questionAnswer_7
    result.questionAnswer.question_8= sharedDataManager.questionAppAnswer.questionAnswer_8

    LaunchedEffect(Unit){
        viewModel.setPageState(result)
    }
    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is ResultPageViewModel.UiEventResult.SaveAnswer -> {
                    navController.navigate(Screen.LoginPage.route)
                }
            }

        }
    }

    val image = painterResource(id = R.drawable.cosmonaut)

    Surface(){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Thank you for completing the survey!", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Image(image, contentDescription = null)
            Button(onClick = {
                             viewModel.onEventResult(ResultPageEvent.SaveAnswer)
            },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)) {
                Text(text = "Finish", fontSize = 20.sp)
            }
        }
    }
}