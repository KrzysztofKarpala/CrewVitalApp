package com.pl.agh.kkarpala.crewvitalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pl.agh.kkarpala.crewvitalapp.ui.theme.CrewVitalAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrewVitalAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var StartCard by remember { mutableStateOf(true) }

    if (StartCard){
        OnboardingScreen(onContinueClicked = { StartCard = false})
    } else{
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            
            Text("Crew Vital Check Application!", fontSize = 25.sp)
            StartCard(onContinueClicked)
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
                bottom = 20.dp)
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
                    .size(width = 200.dp, height = 100.dp),
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
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plese enter your name"
        )
        Spacer(Modifier.height(16.dp))
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
private fun Greetings(names: List<String> = listOf("World", "Compose")){
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names){
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false)}

    val extraPadding = if ( expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Crew Vital Check Application")
                Text(text = name)
            }
            OutlinedButton(
                onClick = {expanded.value = !expanded.value}
            ) {
               Text(if (expanded.value) "Show less" else "Show more")
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrewVitalAppTheme {
        Greeting("Android")
    }
}