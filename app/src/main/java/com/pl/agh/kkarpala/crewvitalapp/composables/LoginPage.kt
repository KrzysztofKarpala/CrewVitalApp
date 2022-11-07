package com.pl.agh.kkarpala.crewvitalapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.pl.agh.kkarpala.crewvitalapp.R
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.navigation.Screen

@Composable
fun LoginPage(navController: NavController){

    val image = painterResource(id = R.drawable.bc)

    val name = remember { mutableStateOf("")}

    var nameHasError = false

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
    {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.TopCenter)
        {
            Image(image, contentDescription = null)
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
        ) {
            Text(
                text = "Sign In",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                fontSize = 38.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                OutlinedTextField(
                    value = name.value,
                    onValueChange = {name.value = it},
                    label = {Text(text = "Name")},
                    placeholder = {Text(text = "Name")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                if(name.value != ""){
                    nameHasError = true
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(enabled = nameHasError, onClick = {navController.navigate(Screen.QuestionPage.withArgs(1)) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)) {
                Text(text = "Start", fontSize = 20.sp)
            }
        }
    }
}