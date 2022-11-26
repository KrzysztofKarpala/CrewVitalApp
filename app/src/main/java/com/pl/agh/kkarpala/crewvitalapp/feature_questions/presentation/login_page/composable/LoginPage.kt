package com.pl.agh.kkarpala.crewvitalapp.feature_questions.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.pl.agh.kkarpala.crewvitalapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.util.Screen
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page.LoginPageEvent
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page.LoginPageViewModel
import com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.login_page.composable.EnterNameTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginPageViewModel = hiltViewModel()
){

    val nameState = viewModel.name.value


    val image = painterResource(id = R.drawable.bc)

    val context = LocalContext.current
/*
    val name = remember { mutableStateOf("")}

    var nameHasError = true

    var questionAppAnswer = QuestionAppDto("", "", "", "", "", 0, 0, 0, 0,)
*/

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is LoginPageViewModel.UiEvent.ShowToast -> {
/*                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )*/
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT)
                        .show()
                }
                is LoginPageViewModel.UiEvent.SaveName -> {
                    navController.navigate(
                        Screen.QuestionPage.withArgs(1))
                }
            }
        }
    }

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
/*                OutlinedTextField(
                    value = name.value,
                    onValueChange = {name.value = it},
                    label = {Text(text = "Name")},
                    placeholder = {Text(text = "Name")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                if(name.value != ""){
                    nameHasError = false
                }*/
                EnterNameTextField(
                    text = nameState.name,
                    hint = nameState.hint,
                    onValueChange = {
                                    viewModel.onEvent(LoginPageEvent.EnteredName(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(LoginPageEvent.ChangeNameFocus(it))
                    },
                    isHintVisible = nameState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
/*            Button( onClick = { questionAppAnswer.userName = name.value; navController.navigate(
                Screen.QuestionPage.withArgs(1)) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)) {
                Text(text = "Start", fontSize = 20.sp)
            }*/
            Button( onClick = {
                              viewModel.onEvent(LoginPageEvent.SaveName)
            },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)) {
                Text(text = "Start", fontSize = 20.sp)
            }
        }
    }
}