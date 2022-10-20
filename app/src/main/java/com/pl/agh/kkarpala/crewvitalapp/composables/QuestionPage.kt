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
import androidx.navigation.NavController

@Composable
fun QuestionPage(navController: NavController){
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("How are you feeling today?", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 45.dp, bottom = 20.dp))
            CustomLinearProgressBar()
            Answers(navController = navController)
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
private fun Answers(answers: List<String> = listOf("Very Good!", "Good", "Average", "Bad", "Very bad"), navController: NavController){
   Column(
       modifier = Modifier
           .padding(vertical = 15.dp),
       verticalArrangement = Arrangement.Center
       ) {
       for (answer in answers){
            Answer(answer = answer)
       }
       Spacer(modifier = Modifier.padding(10.dp))
       SubmitBtn(navController = navController)
   }
}

@Composable
fun Answer(answer: String) {
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
}

@Composable
fun SubmitBtn(navController: NavController){
    Button(onClick = {navController.navigate("login_page") },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(0.8f)
            .height(65.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
    ) {
        Text(text = "Submit", fontSize = 20.sp)
    }
}