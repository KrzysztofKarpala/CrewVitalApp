package com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.core.questions.Constants
import com.pl.agh.kkarpala.crewvitalapp.core.questions.SharedDataManager
import com.pl.agh.kkarpala.crewvitalapp.utils.Screen

@Composable
fun QuestionPage(navController: NavController, questionId: Int, sharedDataManager: SharedDataManager){

    val questionList = Constants.getQuestions()
    val currentQuestion = questionList!![questionId-1]
    var selectedOption by remember { mutableStateOf("")}
    var answerSelected = false
    val onSelectionChange = { text : String -> selectedOption = text}


    Surface() {
        Column(
            modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text("${currentQuestion.question}", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 45.dp, bottom = 20.dp), textAlign = TextAlign.Center)
            CustomLinearProgressBar(questionId-1)
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            //Answers(currentQuestion.answers)
            Column(
                modifier = Modifier
                    .padding(vertical = 15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                currentQuestion.answers.forEach {text ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ){
                        OutlinedButton(
                            onClick = {onSelectionChange(text)},
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth(0.8f)
                                .height(60.dp),
                            elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
                            border = BorderStroke(1.dp, if(text == selectedOption){
                                Color.Blue
                            } else{
                                Color.White
                            })
                        )
                        {
                            Column(modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally)
                            {
                                Text("$text", color = Color.Black, fontSize = 20.sp, textAlign = TextAlign.Center, )
                            }
                            if(selectedOption != ""){
                                answerSelected = true
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.padding(3.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(enabled = answerSelected, onClick = {
                if(questionId == questionList.size){
                    navController.navigate(Screen.OpenQuestionPage.withArgs(1))
                }
                else{
                    navController.navigate(Screen.QuestionPage.withArgs(questionId + 1))
                }
                sharedDataManager.addAnswerToQuestionAppAnswer(selectedOption, questionId)
                                                       },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
            ) {
                Text(text = "Submit", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(38.dp))
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
            progress = progress/8f
        )
    }
}

/*@Composable
private fun Answers(answers: List<String>){

    var selectedOption by remember { mutableStateOf("")}
    val onSelectionChange = { text : String -> selectedOption = text}


    Column(
       modifier = Modifier
           .padding(vertical = 15.dp),
       verticalArrangement = Arrangement.Center
       ) {
        answers.forEach {text ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
            ){
                OutlinedButton(
                    onClick = {onSelectionChange(text)},
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
                    border = BorderStroke(1.dp, if(text == selectedOption){
                        Color.Blue
                    } else{
                        Color.White
                    })
                ){
                    Column(modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text("$text", color = Color.Black, fontSize = 20.sp, textAlign = TextAlign.Center, )
                    }
                }

            }
        }
       Spacer(modifier = Modifier.padding(10.dp))
   }
}*/

/*
@Composable
fun SubmitBtn(answerIsEmpty: Boolean, navController: NavController, nav_route : String, selectedOption: String, sharedDataManager: SharedDataManager){
    Button(enabled = answerIsEmpty, onClick = {navController.navigate(nav_route)
                                              sharedDataManager.addAnswerToQuestionAppAnswer(selectedOption,)},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.8f)
            .height(50.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
    ) {
        Text(text = "Submit", fontSize = 20.sp)
    }
}*/
