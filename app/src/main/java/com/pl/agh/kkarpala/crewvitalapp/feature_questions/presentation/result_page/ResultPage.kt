package com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.R
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.util.Screen


@Composable
fun ResultPage(navController: NavController){

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
            Button(onClick = {navController.navigate(Screen.LoginPage.route) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)) {
                Text(text = "Finish", fontSize = 20.sp)
            }
        }
    }
}