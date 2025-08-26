package com.example.mushroom.view.icon

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.mushroom.R
import kotlinx.coroutines.launch


@Composable
fun SlideToBookButton(
    btnText: String,
    btnTextStyle: TextStyle,
    outerBtnBackgroundColor: Color,
    sliderBtnBackgroundColor: Color,
    @DrawableRes sliderBtnIcon: Int,
    onBtnSwipe: () -> Unit
) {
    val density = LocalDensity.current
    val layoutDir = LocalLayoutDirection.current

    // --- layout constants ---
    val thumbSizeDp = 60.dp
    val leftPadDp = 10.dp
    // space reserved on the right for the arrow hint
    val arrowSizeDp = 18.dp
    val arrowGapDp = 6.dp
    val arrowClusterWidthDp = arrowSizeDp * 2 + arrowGapDp
    // make sure right padding leaves room for arrows
    val rightPadDp = 10.dp

    val thumbSizePx = with(density) { thumbSizeDp.toPx() }
    val leftPadPx = with(density) { leftPadDp.toPx() }
    val rightPadPx = with(density) { rightPadDp.toPx() }

    var boxWidthPx by remember { mutableIntStateOf(0) }

    val sliderX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    val maxOffsetPx by remember {
        derivedStateOf { (boxWidthPx - thumbSizePx - leftPadPx - rightPadPx).coerceAtLeast(0f) }
    }
    val progress by remember {
        derivedStateOf { if (maxOffsetPx == 0f) 0f else (sliderX.value / maxOffsetPx).coerceIn(0f, 1f) }
    }

    var sliderComplete by remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(false) }

    val trackScale by animateFloatAsState(if (sliderComplete) 0f else 1f, tween(300), label = "trackScale")
    val sliderAlpha by animateFloatAsState(if (sliderComplete) 0f else 1f, tween(300), label = "sliderAlpha")
    val textAlpha = 1f - progress
    val confirmThreshold = 0.8f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .onSizeChanged { boxWidthPx = it.width }
    ) {
        // TRACK
        Box(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(scaleX = trackScale, scaleY = 1f)
                .background(outerBtnBackgroundColor, RoundedCornerShape(40.dp))
        ) {
            Text(
                text = btnText,
                style = btnTextStyle,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(textAlpha)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .align(Alignment.Center).padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy((-20).dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.right_arrow),
                contentDescription = "Arrow",
                modifier = Modifier.size(30.dp)
                    .alpha(textAlpha)
            )

            Icon(
                painter = painterResource(R.drawable.right_arrow),
                contentDescription = "Arrow",
                modifier = Modifier.size(30.dp)
                    .alpha(textAlpha)
            )
        }


        // THUMB
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = leftPadDp, end = rightPadDp, top = 10.dp, bottom = 10.dp)
                .offset(x = with(density) { sliderX.value.toDp() })
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        if (sliderComplete) return@rememberDraggableState
                        val dir = if (layoutDir == LayoutDirection.Rtl) -1 else 1
                        val next = (sliderX.value + delta * dir).coerceIn(0f, maxOffsetPx)
                        scope.launch { sliderX.snapTo(next) }
                    },
                    onDragStopped = {
                        if (sliderComplete) return@draggable
                        if (progress >= confirmThreshold) {
                            sliderComplete = true
                            showLoading = true
                            scope.launch {
                                sliderX.animateTo(maxOffsetPx, tween(220))
                                onBtnSwipe()
                            }
                        } else {
                            scope.launch { sliderX.animateTo(0f, tween(180)) }
                        }
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.alpha(sliderAlpha).graphicsLayer { alpha = sliderAlpha }
            ) {
                SliderButton(
                    sliderBtnWidth = thumbSizeDp,              // not used internally, kept for your API
                    sliderBtnBackgroundColor = sliderBtnBackgroundColor,
                    sliderBtnIcon = sliderBtnIcon
                )
            }
        }

        if (showLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    }
}


/**
 *
 * This composable defines the visual appearance of the slider thumb — a small rounded box
 * that contains an icon (usually a car or arrow). It is positioned inside the larger
 * SlideToBookButton and will later be made draggable.
 *
 * @param sliderBtnBackgroundColor Background color for the thumb (distinct from the track)
 * @param sliderBtnIcon Icon displayed at the center of the thumb button
 */
@Composable
private fun SliderButton(
    sliderBtnWidth: Dp, // Width of the button
    sliderBtnBackgroundColor: Color, // Background color for the thumb
    @DrawableRes sliderBtnIcon: Int  // Icon shown inside the thumb
) {
    // Root Box for the slider thumb
    Box(
        modifier = Modifier
            .wrapContentSize()
            .width(60.dp)
            .height(60.dp)
            .background(
                color = sliderBtnBackgroundColor,
                shape = RoundedCornerShape(30.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = Color.White,
                painter = painterResource(sliderBtnIcon),
                contentDescription = "Begin harvest",
            )
        }
    }
}

@Preview
@Composable
fun Preview(){
    SlideToBookButton(
        btnText = "123",
        btnTextStyle = TextStyle.Default,
        outerBtnBackgroundColor = Color("#F1D0BC".toColorInt()),
        sliderBtnBackgroundColor = Color("#CC5308".toColorInt()),
        sliderBtnIcon = R.drawable.begin_harvest,
        onBtnSwipe = {}
    )
}
