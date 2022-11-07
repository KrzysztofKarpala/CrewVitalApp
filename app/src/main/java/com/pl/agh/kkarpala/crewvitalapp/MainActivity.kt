package com.pl.agh.kkarpala.crewvitalapp

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.NumberPicker
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pl.agh.kkarpala.crewvitalapp.composables.LoginPage
import com.pl.agh.kkarpala.crewvitalapp.composables.OpenQuestionPage
import com.pl.agh.kkarpala.crewvitalapp.composables.QuestionPage
import com.pl.agh.kkarpala.crewvitalapp.composables.ResultPage
import com.pl.agh.kkarpala.crewvitalapp.data.models.QuestionListEntry
import com.pl.agh.kkarpala.crewvitalapp.navigation.Screen
import com.pl.agh.kkarpala.crewvitalapp.ui.theme.CrewVitalAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            CrewVitalAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    
/*    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = { LogingPage(navController = navController)})
        composable("question_page/{questionId}",
            content = { QuestionPage(navController = navController)},
            arguments = listOf(navArgument("questionId"){
                type = NavType.IntType
            }))
        composable("open_question_page/{questionId}",
            content = { OpenQuestionPage(navController = navController)},
            arguments = listOf(navArgument("questionId") {
                type = NavType.IntType
            }))
    }
    )*/
    NavHost(navController = navController, startDestination = Screen.LoginPage.route){
        composable(Screen.LoginPage.route){
            LoginPage(navController = navController)
        }
        composable(Screen.QuestionPage.route + "/{questionId}",
            arguments = listOf(
                navArgument("questionId"){
                type = NavType.IntType
            })) { entry ->
                QuestionPage(navController = navController, questionId = entry.arguments!!.getInt("questionId"))
        }
        composable(Screen.OpenQuestionPage.route+"/{questionId}",
            arguments = listOf(
                navArgument("questionId"){
                    type = NavType.IntType
                })){ entry ->
            OpenQuestionPage(navController = navController, questionId = entry.arguments!!.getInt("questionId"))
        }
        composable(Screen.ResultPage.route){
            ResultPage(navController = navController)
        }
    }
}

/*@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Box() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Text("Crew Vital Check Application", fontSize = 25.sp, modifier = Modifier.padding(top = 45.dp))
                StartCard(onContinueClicked)
            }
        }
    }
}

@Composable
fun StartCard(onContinueClicked: () -> Unit){
    Card(
        modifier = Modifier
            .padding(
                top = 60.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            )
            .fillMaxWidth(),
        elevation = 20.dp
    ){
        Column(modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome!", fontSize = 30.sp, textAlign = TextAlign.Center)
            EnterName()
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth()
                    .size(width = 200.dp, height = 70.dp),
                onClick = onContinueClicked
            ){
                Text("Start", fontSize = 25.sp)
            }
        }
    }
}


@Composable
fun EnterName(){
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plese enter your name"
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth())
        EditNameField()
    }
}

@Composable
fun EditNameField(){
    var name by remember {
        mutableStateOf("")
    }

    TextField(
        label = { Text(stringResource(R.string.name))},
        modifier = Modifier.fillMaxWidth(),
        value = name,
        singleLine = true,
        onValueChange = { name = it}
    )
}

@Composable
fun Question(){
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text("How are you feeling today?", fontSize = 25.sp, modifier = Modifier.padding(top = 45.dp, bottom = 20.dp))
            CustomLinearProgressBar()
            Answers()
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
private fun Answers(answers: List<String> = listOf("Very Good!", "Good", "Average", "Bad", "Very bad")){
    Column(
        modifier = Modifier
        .padding(vertical = 15.dp),
        verticalArrangement = Arrangement.Center
    ) {
        for (answer in answers){
            Answer(answer = answer)
        }
    }
}

@Composable
fun Answer(answer: String) {
    var selected by remember { mutableStateOf(false)}
    val color = if (selected) Color.Blue else Color.White
    OutlinedButton(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        onClick = {selected =! selected},
        border = BorderStroke(1.dp, color)
    ){
        Column(modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("$answer", fontSize = 20.sp, textAlign = TextAlign.Center)

        }
    }
}*/
