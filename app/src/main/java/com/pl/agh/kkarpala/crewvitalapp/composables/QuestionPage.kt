package com.pl.agh.kkarpala.crewvitalapp.composables

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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.data.Constants
import com.pl.agh.kkarpala.crewvitalapp.data.models.QuestionListEntry
import com.pl.agh.kkarpala.crewvitalapp.navigation.Screen

@Composable
fun QuestionPage(navController: NavController, questionId: Int){

    val questionList = Constants.getQuestions()
    val currentQuestion = questionList!![questionId-1]

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("${currentQuestion.question}", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 45.dp, bottom = 20.dp))
            CustomLinearProgressBar()
            Answers()
            Spacer(modifier = Modifier.padding(3.dp))
            if(questionId == questionList.size){
                SubmitBtn(navController = navController, Screen.OpenQuestionPage.withArgs(1))
            }
            else{
                SubmitBtn(navController = navController, Screen.QuestionPage.withArgs(questionId + 1))

            }
        }
    }
}

@Composable
private fun CustomLinearProgressBar(){
    Column(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
                .padding(start = 30.dp, end = 30.dp),
            backgroundColor = Color.LightGray,
            color = Color.Green,
            progress = 0.3f
        )
    }
}

@Composable
private fun Answers(answers: List<String> = listOf("Very Good!", "Good", "Average", "Bad")){

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
}

/*@Composable
private fun Answer(answer: String) {
    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Blue else Color.White
    OutlinedButton(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.8f)
            .height(60.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        onClick = {selected =! selected},
        border = BorderStroke(1.dp, color)
    ){
        Column(modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("$answer", color = Color.Black, fontSize = 20.sp, textAlign = TextAlign.Center, )
        }
    }
}*/

@Composable
fun SubmitBtn(navController: NavController, nav_route : String){
    Button(onClick = {navController.navigate(nav_route) },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.8f)
            .height(50.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
    ) {
        Text(text = "Submit", fontSize = 20.sp)
    }
}