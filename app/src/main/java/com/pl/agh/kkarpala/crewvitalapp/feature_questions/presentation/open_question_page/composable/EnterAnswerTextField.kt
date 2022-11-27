package com.pl.agh.kkarpala.crewvitalapp.feature_questions.presentation.open_question_page.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EnterAnswerTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = true,
    onFocusChange: (FocusState) -> Unit
){
    Box(
        modifier = modifier
    ){
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .onFocusChanged {
                    onFocusChange(it)
                },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if(isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}