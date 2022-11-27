package com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.core.questions.OpenConstants
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page.LoginPageEvent
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page.LoginPageViewModel
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page.composable.EnterNameTextField
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page.OpenQuestionPageEvent
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page.OpenQuestionPageViewModel
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page.composable.EnterAnswerTextField
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OpenQuestionPage(navController: NavController,
                     questionId: Int,
                     viewModel: OpenQuestionPageViewModel = hiltViewModel(),
                     sharedDataManager: SharedDataManager
                     ){

    val openQuestionList = OpenConstants.getQuestions()
    val currentQuestion = openQuestionList!![questionId-1]

    val answerState = viewModel.answerOpen.value

    val context = LocalContext.current



    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is OpenQuestionPageViewModel.UiEventOpen.ShowToast-> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT)
                        .show()
                }
                is OpenQuestionPageViewModel.UiEventOpen.SaveOpenAnswer -> {
                    if (questionId == openQuestionList.size){
                    navController.navigate(
                        Screen.ResultPage.route)
                    }
                    else{
                        Screen.OpenQuestionPage.withArgs(questionId + 1)
                    }

                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top)
        {
            Text(
                "${currentQuestion.question}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 45.dp, bottom = 20.dp),
                textAlign = TextAlign.Center
            )
            CustomLinearProgressBar(questionId - 1)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.68f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(color = Color.White)
                .padding(10.dp)
        )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.68f)
                        .background(color = Color.White)
                        .padding(10.dp)
                ){
                    EnterAnswerTextField(
                        text = answerState.answer,
                        hint = answerState.hint,
                        onValueChange = {
                            viewModel.onEventOpen(OpenQuestionPageEvent.EnteredAnswer(it))
                            sharedDataManager.addAnswerToQuestionAppAnswer(it, questionId + 4)
                        },
                        onFocusChange = {
                            viewModel.onEventOpen(OpenQuestionPageEvent.ChangedAnswerFocus(it))
                        },
                        isHintVisible = answerState.isHintVisible,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h5
                    )
                }
/*                if (questionId == openQuestionList.size) {
                    SubmitBtn(answerIsEmpty, navController = navController, Screen.ResultPage.route)
                } else {
                    SubmitBtn(answerIsEmpty,
                        navController = navController,
                        Screen.OpenQuestionPage.withArgs(questionId + 1)
                    )
                }*/
                Button( onClick = {
                    viewModel.onEventOpen(OpenQuestionPageEvent.SaveOpenAnswer)
                },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)){
                    Text("Submit", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(50.dp))
            }
        }
}

@Composable
private fun CustomLinearProgressBar(progress: Int){
    Column(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
                .padding(start = 30.dp, end = 30.dp),
            backgroundColor = Color.LightGray,
            color = Color.Green,
            progress = (progress+4)/8f
        )
    }
}

/*@Composable
private fun Answer(){

    val answer = remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.68f)
            .background(color = Color.White)
            .padding(10.dp)
    ){
        OutlinedTextField(value = answer.value, onValueChange = {answer.value = it},
            label = {Text(text = "Answer")},
            placeholder = {Text(text = "Answer")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}*/
