package com.pl.agh.kkarpala.crewvitalapp.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.data.models.QuestionListEntry
import com.pl.agh.kkarpala.crewvitalapp.navigation.Screen

@Composable
fun OpenQuestionPage(navController: NavController, questionId: Int){

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("${questionId}", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 45.dp, bottom = 20.dp))
            CustomLinearProgressBar()
            Answer()
            Spacer(modifier = Modifier.padding(2.dp))
            SubmitBtn(navController = navController, Screen.LoginPage.route)
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
}
