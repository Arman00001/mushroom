package com.example.mushroom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.toColorInt
import com.example.mushroom.R
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.icon.SlideToBookButton
import com.example.mushroom.view.screen.StepProgressIndicator

private enum class HarvestStep {
    SelectShelves,
    ChooseDiameter,
    ChangeDiameterPassword,
    EditDiameter,
    SelectHarvestType,
    InputHarvestCount,
    ChooseDistanceInM,
    ChooseWeight,
    Start,
    StartPassword
}

@Composable
fun PopupTestingBox(
    shelfIds: List<Int>,
    initiallySelected: Set<Int> = emptySet(),
    open: Boolean,
    onDismiss: () -> Unit,
    boxContainerColor: Color = COLORS.PopUpErrorBoxContainer,
    containerColor: Color = COLORS.ErrorContainer,
    borderColor: Color = COLORS.StatusErrorContainer
) {
    if (!open) return

    var step by rememberSaveable { mutableStateOf(HarvestStep.SelectShelves) }

    val selected = rememberSaveable(
        inputs = arrayOf(shelfIds)
    ) { mutableStateOf(initiallySelected) }

    var minSize by rememberSaveable { mutableStateOf(2) }
    var maxSize by rememberSaveable { mutableStateOf(6) }

    var inputAmountOfHarvest by remember { mutableStateOf("") }
    var inputDistance by remember { mutableStateOf("") }
    var inputWeight by remember { mutableStateOf("") }


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
                .fillMaxHeight(0.9f)
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(20.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = when (step) {
                            HarvestStep.SelectShelves -> "Select shelves"

                            HarvestStep.ChooseDiameter,
                            HarvestStep.ChangeDiameterPassword,
                            HarvestStep.EditDiameter -> "Changing diameter"

                            HarvestStep.SelectHarvestType -> "Select harvest type"

                            HarvestStep.InputHarvestCount -> "Input the desired number of mushrooms"

                            HarvestStep.ChooseDistanceInM -> "Choose distance in metres"

                            HarvestStep.ChooseWeight -> "Choose the desired weight"

                            HarvestStep.Start, HarvestStep.StartPassword -> "Start harvest"
                        },
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

                Column(
                    modifier = Modifier.fillMaxWidth(0.95f),
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StepProgressIndicator(
                        modifier = Modifier.padding(horizontal = 40.dp),
                        iconSize = 40.dp,
                        circleSize = 70.dp,
                        painters = listOf(
                            painterResource(R.drawable.shelf_harvest),
                            painterResource(R.drawable.size_harvest),
                            painterResource(R.drawable.select_harvest_type),
                            painterResource(R.drawable.start_2)
                        ),
                        currentStep = when (step) {
                            HarvestStep.SelectShelves -> 0

                            HarvestStep.ChooseDiameter,
                            HarvestStep.ChangeDiameterPassword,
                            HarvestStep.EditDiameter -> 1

                            HarvestStep.SelectHarvestType,
                            HarvestStep.InputHarvestCount,
                            HarvestStep.ChooseDistanceInM,
                            HarvestStep.ChooseWeight -> 2

                            HarvestStep.Start,
                            HarvestStep.StartPassword -> 3
                        }
                    )


                    CurrentStepInfo(
                        step = step,
                        value = selected.value.sorted(),
                        minSize = minSize,
                        maxSize = maxSize,
                        collectTypeName =
                            if (inputAmountOfHarvest.isBlank()
                                && inputDistance.isBlank()
                                && inputWeight.isBlank()
                            ) "Choose harvest type"
                            else if (inputAmountOfHarvest.isNotBlank()) "Amount"
                            else if (inputDistance.isNotBlank()) "Distance"
                            else "Weight",
                        collectTypeAmount =
                            if (inputAmountOfHarvest.isBlank()
                                && inputDistance.isBlank()
                                && inputWeight.isBlank()
                            ) null
                            else if (inputAmountOfHarvest.isNotBlank()) "$inputAmountOfHarvest pieces"
                            else if ((inputDistance.isNotBlank())) "$inputDistance Metres"
                            else "$inputWeight kg"
                    )
                }

                Spacer(Modifier.weight(1f))

                when (step) {
                    HarvestStep.SelectShelves -> SelectShelvesStep(
                        shelfIds = shelfIds,
                        selected = selected,
                        onToggle = { id ->
                            selected.value = selected.value.toMutableSet().also {
                                if (!it.add(id)) it.remove(id)
                            }
                        },
                        onDismiss = onDismiss,
                        onContinue = {
                            if (selected.value.isNotEmpty()) step =
                                HarvestStep.ChooseDiameter
                        }
                    )

                    HarvestStep.ChooseDiameter -> ChooseDiameterStep(
                        selected = selected.value.sorted(),
                        minSize = minSize,
                        maxSize = maxSize,
                        onDismiss = onDismiss,
                        onContinue = { step = HarvestStep.SelectHarvestType },
                        onChangePressed = { step = HarvestStep.ChangeDiameterPassword }
                    )

                    HarvestStep.ChangeDiameterPassword -> PasswordStep(
                        onCancel = { step = HarvestStep.ChooseDiameter },
                        onVerified = { code ->
                            // TODO validate
                            step = HarvestStep.EditDiameter
                        }
                    )

                    HarvestStep.EditDiameter -> EditDiameterStep(
                        minSize = minSize,
                        maxSize = maxSize,
                        onMinChange = { minSize = it.coerceAtMost(maxSize) },
                        onMaxChange = { maxSize = it.coerceAtLeast(minSize) },
                        onDone = { step = HarvestStep.SelectHarvestType }, // back to summary
                        onCancel = { step = HarvestStep.ChooseDiameter }
                    )

                    HarvestStep.SelectHarvestType -> SelectHarvestTypeStep(
                        onClickHarvestCount = { step = HarvestStep.InputHarvestCount },
                        onClickDistance = { step = HarvestStep.ChooseDistanceInM },
                        onClickWeight = { step = HarvestStep.ChooseWeight },
                        onDismiss = onDismiss
                    )

                    HarvestStep.InputHarvestCount -> InputHarvestCountStep(
                        onContinue = { step = HarvestStep.Start },
                        onDismiss = onDismiss,
                        inputAmountOfHarvest = inputAmountOfHarvest,
                        onAmountChange = { inputAmountOfHarvest = it }
                    )

                    HarvestStep.ChooseDistanceInM -> ChooseDistanceInMStep(
                        onContinue = { step = HarvestStep.Start },
                        onDismiss = onDismiss,
                        inputDistance = inputDistance,
                        onDistanceChange = { inputDistance = it },
                    )

                    HarvestStep.ChooseWeight -> ChooseHarvestWeightStep(
                        onContinue = { step = HarvestStep.Start },
                        onDismiss = onDismiss,
                        inputWeight = inputWeight,
                        onWeightChange = { inputWeight = it }
                    )

                    HarvestStep.Start -> StartHarvestStep(
                        shelfNumbers = selected.value.sorted(),
                        minSize = minSize,
                        maxSize = maxSize,
                        onButtonSwipe = { step = HarvestStep.StartPassword }
                    )

                    HarvestStep.StartPassword -> PasswordStep(
                        onCancel = { step = HarvestStep.Start },
                        onVerified = { code ->
                            // TODO validate
                            step = HarvestStep.Start
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChooseHarvestWeightStep(
    onContinue: () -> Unit,
    onDismiss: () -> Unit,
    inputWeight: String,
    onWeightChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(35.dp)

    OutlinedTextField(
        value = inputWeight,
        onValueChange = { newText ->
            if (newText.all { it.isDigit() }) {
                onWeightChange(newText)
            }
        },
        placeholder = { Text("Type the weight") },
        singleLine = true,
        shape = RoundedCornerShape(35.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            disabledContainerColor = Color.Gray,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color("#858378".toColorInt()),
            unfocusedIndicatorColor = Color("#858378".toColorInt()),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )

    CancelContinueButtons(onDismiss = onDismiss, onContinue = onContinue)
}

@Composable
fun ChooseDistanceInMStep(
    onContinue: () -> Unit,
    onDismiss: () -> Unit,
    inputDistance: String,
    onDistanceChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(35.dp)
    var value by remember { mutableDoubleStateOf(0.5) }

    val stepCount = 0.5

    Row(
        modifier = Modifier
            .fillMaxWidth(0.34f)
            .border(
                width = 1.dp,
                shape = shape,
                color = Color("#858378".toColorInt())
            )
            .background(
                shape = shape,
                color = Color.White
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundedIconButton(
            contentDescription = "minus",
            painter = painterResource(R.drawable.minus),
            start = 10.dp,
            end = 10.dp,
            top = 5.dp,
            bottom = 5.dp,
            onClick = {
                if (value - stepCount > 0) {
                    value -= stepCount
                    onDistanceChange(value.toString())
                }
            },
            containerColor = Color("#EAE9DD".toColorInt()),
            borderColor = Color("#C7C5B4".toColorInt()),
            cornerSize = 25.dp,
            borderWidth = 1.dp,
        )
        Text(
            text = "$value (m)",
            fontSize = 22.sp,
            fontWeight = FontWeight.W400
        )

        RoundedIconButton(
            contentDescription = "plus",
            painter = painterResource(R.drawable.plus),
            start = 10.dp,
            end = 10.dp,
            top = 5.dp,
            bottom = 5.dp,
            onClick = {
                value += stepCount
                onDistanceChange(value.toString())
            },
            containerColor = Color("#F1D0BC".toColorInt()),
            borderColor = Color("#C7C5B4".toColorInt()),
            cornerSize = 25.dp,
            borderWidth = 1.dp,
        )
    }

    CancelContinueButtons(onDismiss = onDismiss, onContinue = onContinue)
}

@Composable
fun InputHarvestCountStep(
    onContinue: () -> Unit,
    onDismiss: () -> Unit,
    inputAmountOfHarvest: String,
    onAmountChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(35.dp)

    OutlinedTextField(
        value = inputAmountOfHarvest,
        onValueChange = { newText ->
            if (newText.all { it.isDigit() }) {
                onAmountChange(newText)
            }
        },
        placeholder = { Text("Type the amount") },
        singleLine = true,
        shape = RoundedCornerShape(35.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            disabledContainerColor = Color.Gray,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color("#858378".toColorInt()),
            unfocusedIndicatorColor = Color("#858378".toColorInt()),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )

    CancelContinueButtons(onDismiss = onDismiss, onContinue = onContinue)
}

@Composable
fun SelectHarvestTypeStep(
    onDismiss: () -> Unit,
    onClickHarvestCount: () -> Unit,
    onClickDistance: () -> Unit,
    onClickWeight: () -> Unit
) {
    val radioOptions = listOf(
        Pair("By harvest amount", onClickHarvestCount),
        Pair("By distance on shelf", onClickDistance),
        Pair("By weight", onClickWeight)
    )
//    val radioOptions = listOf("By harvest amount", "By distance on shelf", "By weight")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val shape = RoundedCornerShape(20.dp)


    FlowRow(
        modifier = Modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        maxItemsInEachRow = 2
    ) {
        radioOptions.forEach { pair ->
            val text = pair.first

            Box(
                modifier = Modifier
                    .clip(shape)
                    .border(
                        width = 1.dp,
                        color = Color("#A8A698".toColorInt()),
                        shape = shape
                    )
                    .background(
                        color = if (text == selectedOption.first) COLORS.PageColor else COLORS.PopUpErrorBoxContainer,
                        shape = shape
                    )
                    .then(
                        Modifier
                            .clip(shape)
                            .clickable(
                                interactionSource = null,
                                indication = ripple(true),
                                onClick = {
                                    onOptionSelected(pair)
                                },
                                role = Role.RadioButton
                            )

                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(0.12f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption.first),
                        colors = RadioButtonColors(
                            selectedColor = COLORS.PopUpButtonContainer,
                            unselectedColor = COLORS.PopUpButtonContainer,
                            disabledSelectedColor = COLORS.PopUpButtonContainer,
                            disabledUnselectedColor = COLORS.PopUpButtonContainer
                        ),
                        onClick = null
                    )
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }

    CancelContinueButtons(onDismiss, selectedOption.second)
}

private fun milestone(step: HarvestStep): Int = when (step) {
    HarvestStep.SelectShelves -> 0
    HarvestStep.ChooseDiameter,
    HarvestStep.ChangeDiameterPassword,
    HarvestStep.EditDiameter -> 1

    HarvestStep.SelectHarvestType,
    HarvestStep.InputHarvestCount,
    HarvestStep.ChooseDistanceInM,
    HarvestStep.ChooseWeight -> 2

    HarvestStep.Start,
    HarvestStep.StartPassword -> 3
}

@Composable
private fun Pill(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String? = null,
    containerColor: Color = Color("#EAE9DD".toColorInt())
) {
    Column(
        modifier = modifier
//            .fillMaxWidth(0.13f)
//            .fillMaxHeight(0.12f)
//            .width(120.dp)
            .height(56.dp)
            .background(
                color = containerColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(10.dp),
//            .padding(vertical = 20.dp, horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically)
    ) {
        if (title != null) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                color = COLORS.DarkColorText
            )
        }
        if (value != null) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.W700
            )
        }
    }
}

@Composable
private fun CurrentStepInfo(
    step: HarvestStep,
    value: List<Int>,
    minSize: Int,
    maxSize: Int,
    collectTypeName: String,
    collectTypeAmount: String? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(90.dp)
    ) {
        if (milestone(step) > 0) {
            Pill(
                modifier = Modifier.weight(1f),
                title = "Shelves",
                value = value.joinToString(),
            )
        } else {
            Pill(
                modifier = Modifier.weight(1f),
                title = "Select shelves"
            )
        }

        if (milestone(step) >= 1) {
            Pill(
                modifier = Modifier.weight(1f),
                title = "Diameter",
                value = "$minSize - $maxSize cm",
            )
        } else {
            Pill(
                modifier = Modifier.weight(1f),
                containerColor = COLORS.PopUpErrorBoxContainer
            )
        }

        if (milestone(step) >= 2) {
            Pill(
                modifier = Modifier.weight(1f),
                title = collectTypeName,
                value = collectTypeAmount,
            )
        } else {
            Pill(
                modifier = Modifier.weight(1f),
                containerColor = COLORS.PopUpErrorBoxContainer
            )
        }

        if (milestone(step) >= 3) {
            Pill(
                modifier = Modifier.weight(1f),
                title = "Start"
            )
        } else {
            Pill(
                modifier = Modifier.weight(1f),
                containerColor = COLORS.PopUpErrorBoxContainer
            )
        }
    }
}

@Composable
private fun SelectShelvesStep(
    shelfIds: List<Int>,
    selected: MutableState<Set<Int>>,
    onToggle: (Int) -> Unit,
    onDismiss: () -> Unit,
    onContinue: () -> Unit
) {
    //Select shelves here
    ShelfSelector(
        shelves = shelfIds,
        selected = selected.value,
        onToggle = onToggle
    )


    CancelContinueButtons(onDismiss, onContinue)
}

@Composable
private fun ChooseDiameterStep(
    selected: List<Int>,
    minSize: Int,
    maxSize: Int,
    onChangePressed: () -> Unit,
    onDismiss: () -> Unit,
    onContinue: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color("#EAE9DD".toColorInt()),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Harvest mushrooms of diameter",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = COLORS.DarkColorText
            )
            Text(
                text = "$minSize - $maxSize cm",
                fontSize = 22.sp,
                fontWeight = FontWeight.W400
            )
        }

        RoundedIconButton(
            text = "Change diameter",
            textSize = 16.sp,
            textFontWeight = FontWeight.W500,
            contentColor = COLORS.PopUpButtonContainer,
            containerColor = COLORS.PopUpErrorBoxContainer,
            contentDescription = "change diameter",
            painter = painterResource(R.drawable.mushroom_filled),
            horizontalSpace = 8.dp,
            borderColor = Color("#C7C5B4".toColorInt()),
            borderWidth = 1.dp,
            cornerSize = 30.dp,
            start = 20.dp,
            end = 20.dp,
            onClick = onChangePressed
        )
    }

    CancelContinueButtons(onDismiss, onContinue)
}

@Composable
fun PasswordStep(
    onCancel: () -> Unit,
    onVerified: (String) -> Unit
) {
    var error by rememberSaveable { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .align(Alignment.TopCenter)
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Enter passcode to edit diameter",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )

            SixDigitCodeInline(
                isError = error != null,
                helperText = error ?: "You can paste a 6-digit code.",
                onComplete = { code ->
                    val ok = /* TODO: your check */ code == "123456"
                    if (ok) {
                        error = null
                        onVerified(code)
                    } else {
                        error = "Incorrect passcode. Try again."
                    }
                }
            )
        }
    }

    Row(
        modifier = Modifier.padding(25.dp),
    ) {
        RoundedIconButton(
            text = "Cancel",
            textSize = 16.sp,
            textFontWeight = FontWeight.W700,
            containerColor = Color("#E2E9E5".toColorInt()),
            borderWidth = (-1).dp,
            cornerSize = 35.dp,
            start = 40.dp,
            end = 40.dp,
            top = 20.dp,
            bottom = 20.dp,
            contentDescription = "Cancel",
            onClick = onCancel
        )
    }
}


@Composable
fun EditDiameterStep(
    minSize: Int,
    maxSize: Int,
    onMinChange: (Int) -> Unit,
    onMaxChange: (Int) -> Unit,
    onDone: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Adjust diameter range", fontSize = 16.sp, fontWeight = FontWeight.W500)

        // TODO replace with your scroll/slider UI
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // Example increments
            RoundedIconButton(
                text = "- Min", onClick = { onMinChange((minSize - 1).coerceAtLeast(1)) },
                contentDescription = ""
            )
            RoundedIconButton(
                text = "+ Min", onClick = { onMinChange(minSize + 1) },
                contentDescription = ""
            )
            RoundedIconButton(
                text = "- Max", onClick = { onMaxChange((maxSize - 1).coerceAtLeast(minSize)) },
                contentDescription = ""
            )
            RoundedIconButton(
                text = "+ Max", onClick = { onMaxChange(maxSize + 1) },
                contentDescription = ""
            )
        }

        Text("$minSize – $maxSize cm", fontSize = 22.sp, fontWeight = FontWeight.W400)

        CancelContinueButtons(onCancel, onDone)
    }
}

@Composable
private fun CancelContinueButtons(
    onDismiss: () -> Unit,
    onContinue: () -> Unit,
    cancelColor: Color = Color("#E2E9E5".toColorInt()),
    continueColor: Color = COLORS.PopUpButtonContainer
) {
    Row(
        modifier = Modifier.padding(25.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        RoundedIconButton(
            text = "Cancel",
            textSize = 16.sp,
            textFontWeight = FontWeight.W700,
            containerColor = cancelColor,
            borderWidth = (-1).dp,
            cornerSize = 35.dp,
            start = 40.dp,
            end = 40.dp,
            top = 20.dp,
            bottom = 20.dp,
            contentDescription = "Cancel",
            onClick = onDismiss
        )
        RoundedIconButton(
            text = "Continue",
            textSize = 16.sp,
            textFontWeight = FontWeight.W700,
            containerColor = continueColor,
            contentColor = Color.White,
            borderWidth = (-1).dp,
            cornerSize = 35.dp,
            start = 33.dp,
            end = 33.dp,
            top = 20.dp,
            bottom = 20.dp,
            contentDescription = "Continue",
            onClick = onContinue
        )
    }
}

@Composable
private fun StartHarvestStep(
    shelfNumbers: List<Int>,
    minSize: Int,
    maxSize: Int,
    onButtonSwipe: () -> Unit
) {
    Column {
        Text(
            text = "To start harvesting, swipe right",
            fontSize = 24.sp,
            fontWeight = FontWeight.W400,
            color = COLORS.DarkColorText
        )
    }

//    Row(
//        horizontalArrangement = Arrangement.spacedBy(20.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .background(
//                    color = Color("#EAE9DD".toColorInt()),
//                    shape = RoundedCornerShape(25.dp)
//                )
//                .padding(vertical = 16.dp, horizontal = 12.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Text(
//                text = "Selected shelves",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W500,
//                color = COLORS.DarkColorText
//            )
//            Text(
//                text = shelfNumbers.joinToString(", "),
//                fontSize = 22.sp,
//                fontWeight = FontWeight.W400
//            )
//        }
//
//        Column(
//            modifier = Modifier
//                .background(
//                    color = Color("#EAE9DD".toColorInt()),
//                    shape = RoundedCornerShape(25.dp)
//                )
//                .padding(vertical = 16.dp, horizontal = 12.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//
//        ) {
//            Text(
//                text = "Harvest mushrooms of diameter",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.W500,
//                color = COLORS.DarkColorText
//            )
//            Text(
//                text = "$minSize - $maxSize cm",
//                fontSize = 22.sp,
//                fontWeight = FontWeight.W400
//            )
//        }
//    }
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(25.dp)
    ) {
        SlideToBookButton(
            btnText = "Begin harvest",
            btnTextStyle = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.W400,
                color = COLORS.PopUpButtonContainer,
            ),
            outerBtnBackgroundColor = Color("#F1D0BC".toColorInt()),
            sliderBtnBackgroundColor = Color("#CC5308".toColorInt()),
            sliderBtnIcon = R.drawable.begin_harvest,
            onBtnSwipe = onButtonSwipe
        )
    }


}

@Composable
private fun ShelfSelector(
    shelves: List<Int>,
    selected: Set<Int>,
    onToggle: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .heightIn(min = 160.dp, max = 210.dp) // scrolls if many
        ) {
            items(shelves.size) { index ->
                val item = shelves[index]
                ShelfCheckbox(
                    shelfNumber = index + 1,
                    checked = item in selected,
                    onToggle = { onToggle(item) }
                )
            }
        }
    }
}

@Composable
private fun ShelfCheckbox(
    shelfNumber: Int,
    checked: Boolean,
    onToggle: () -> Unit
) {
    val shape = RoundedCornerShape(size = 16.dp)

    Box(
        modifier = Modifier
            .clip(shape = shape)
            .border(
                shape = shape,
                color = Color("#A8A698".toColorInt()),
                width = 1.dp
            )
            .background(
                shape = shape,
                color = if (checked) Color("#F1D0BC".toColorInt()) else COLORS.PopUpErrorBoxContainer
            )
            .then(
                Modifier
                    .clip(shape)
                    .clickable(
                        interactionSource = null,
                        indication = ripple(true),
                        onClick = onToggle
                    )
            )
            .padding(5.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = COLORS.PopUpButtonContainer),
            )
            Text(
                text = "SHELF $shelfNumber",
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun SixDigitCodeInline(
    isError: Boolean,
    helperText: String?,
    onComplete: (String) -> Unit
) {
    val focus = remember { FocusRequester() }
    var code by rememberSaveable { mutableStateOf("") }

    // hidden text field capturing digits
    BasicTextField(
        value = code,
        onValueChange = { raw ->
            val filtered = raw.take(6)
            code = filtered
            if (filtered.length == 6) onComplete(filtered)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(fontSize = 1.sp),
        modifier = Modifier
            .size(1.dp)
            .alpha(0f)
            .focusRequester(focus)
    )

    // visual line of six slots
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focus.requestFocus()
                }
        ) {
            repeat(6) { idx ->
                val filled = idx < code.length
                CodeDot(
                    filled = filled,
                    highlight = idx == code.length && code.length < 6,
                    error = isError
                )
            }
        }
        if (!helperText.isNullOrBlank()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = helperText,
                color = if (isError) Color(0xFFB00020) else Color.Gray,
                fontSize = 12.sp
            )
        }
    }

    LaunchedEffect(Unit) { focus.requestFocus() }
}

@Composable
private fun CodeDot(filled: Boolean, highlight: Boolean, error: Boolean) {
    val shape = RoundedCornerShape(20.dp)
    val bg = if (error) Color(0xFFFFECEC) else Color(0xFFF4F4F4)
    val border = when {
        error -> Color(0xFFB00020)
        highlight -> Color("#969588".toColorInt())
        else -> Color("#969588".toColorInt())
    }
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(shape)
            .background(bg)
            .border(2.dp, border, shape),
        contentAlignment = Alignment.Center
    ) {
        if (filled) Box(
            modifier = Modifier
                .size(25.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Black.copy(alpha = 0.85f))
        )
    }
}


@Preview(device = Devices.TABLET, showSystemUi = true)
@Composable
fun Preview() {
    val shelves = remember {
        listOf(
            1, 2, 3, 7, 11, 123, 154, 16, 18, 12, 13, 14, 15, 17, 19, 199, 1999, 133, 1333
        )
    }

    PopupTestingBox(
        open = true,
        onDismiss = { println("close") },
        shelfIds = shelves,
        initiallySelected = setOf(7),
    )
}