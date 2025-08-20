package com.example.rickandmorty.ui.activity

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AnimationScreen(

) {
    val buttonInteractionSource = remember { MutableInteractionSource() }
    val buttonPressedState = buttonInteractionSource.collectIsPressedAsState()
    val animButtonSizeWidth = animateDpAsState(
        targetValue = if (buttonPressedState.value) 200.dp else 120.dp
    )
    val animButtonSizeHeight = animateDpAsState(
        targetValue = if (buttonPressedState.value) 70.dp else 48.dp
    )
    val animButtonColor = animateColorAsState(
        targetValue = if (buttonPressedState.value) Color.Red else Color.Blue
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Spacer(
            modifier = Modifier.size(80.dp)
        )
        Button(
            modifier = Modifier.size(width = animButtonSizeWidth.value, height = animButtonSizeHeight.value),

            onClick = {

            },
            interactionSource = buttonInteractionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = animButtonColor.value
            )
        ) {
            Text(
                text = "Geeks",
            )
        }
    }
}