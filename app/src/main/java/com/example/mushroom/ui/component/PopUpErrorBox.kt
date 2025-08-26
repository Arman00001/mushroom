package com.example.mushroom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mushroom.R
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.icon.RoundedIconButton


@Composable
fun PopupErrorBox(
    id: Int = 1,
    open: Boolean,
    onDismiss: ()->Unit,
    boxContainerColor: Color = COLORS.PopUpErrorBoxContainer,
    errorContainerColor: Color = COLORS.ErrorContainer,
    errorBorderColor: Color = COLORS.StatusErrorContainer
) {
    if (!open) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(25.dp),
            color = boxContainerColor,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.8f)
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(20.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Error",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(Modifier.weight(1f))

                    IconButton(
                        onClick = onDismiss
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "close"
                        )
                    }
                }


                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .fillMaxHeight(0.45f)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                shape = RoundedCornerShape(14.dp),
                                color = errorBorderColor,
                                width = 2.dp
                            )
                            .background(
                                color = errorContainerColor,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(R.drawable.close),
                                contentDescription = "Error message",
                                modifier = Modifier
                                    .size(74.dp)
                                    .padding(4.dp)
                            )
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "Lost connection with manipulator $id, for futrhrteei njfsdsjfdkj skjsfdk jsdljf kjsdjfl kjsdf",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(12.dp)
                            .fillMaxWidth(0.6f),
                        text = "Please, press the button below to try again (print a message)\nfor futrhrteei njfsdsjfdkj skjsfdk jsdljf kjsdjfl kjsdffor futrhrteei njfsdsjfdkj skjsfdk jsdljf kjsdjfl kjsdffor futrhrteei njfsdsjfdkj skjsfdk jsdljf kjsdjfl ",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Box(modifier = Modifier.padding(25.dp)) {
                    RoundedIconButton(
                        text = "Check Connection",
                        containerColor = COLORS.PopUpButtonContainer,
                        contentColor = Color.White,
                        borderWidth = (-1).dp,
                        cornerSize = 25.dp,
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 17.dp,
                        end = 17.dp,
                        contentDescription = "Check connection",
                        onClick = { println("Clicked \"check connection\"") }
                    )
                }
            }
        }
    }
}

@Preview(device = Devices.TABLET, showSystemUi = true)
@Composable
fun PopupPreview() {
//    PopupBox(open = true,)
}