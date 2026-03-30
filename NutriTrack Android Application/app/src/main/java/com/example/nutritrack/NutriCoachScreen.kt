package com.example.nutritrack

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritrack.data.foodIntake.FoodIntake
import com.example.nutritrack.data.network.FruitViewModel
import com.example.nutritrack.data.nutricoachtips.NutriCoachTips
import com.example.nutritrack.data.nutricoachtips.NutriCoachTipsViewModel
import com.example.nutritrack.data.patient.PatientViewModel
import com.example.nutritrack.genAI.GenAiViewModel
import com.example.nutritrack.genAI.UiState

// To be done in later assignment
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriCoach(modifier: Modifier = Modifier,
               genAiViewModel: GenAiViewModel = viewModel(),
               fruitViewModel: FruitViewModel = viewModel()
) {

    // Setting Context
    val context = LocalContext.current

    // Patient ViewModel for fruitscore.
    val patientViewModel: PatientViewModel = viewModel(
        factory = PatientViewModel.PatientViewModelFactory(context.applicationContext as Application)
    )

    // NutriCoachTips Viewmodel for collecting prompts results.
    val nutriCoachTipsViewModel : NutriCoachTipsViewModel = viewModel(
        factory = NutriCoachTipsViewModel.NutriCoachTipsViewModelFactory(context.applicationContext as Application)
    )

    // Temp values for text fields.
    val fruitSearch = remember {mutableStateOf("")}
    val state by fruitViewModel.uiState.collectAsState()

    val fruitFamily = remember { mutableStateOf("") }
    val fruitCalories = remember { mutableStateOf("") }
    val fruitFat = remember { mutableStateOf("") }
    val fruitSugar = remember { mutableStateOf("") }
    val fruitCarbs = remember { mutableStateOf("") }
    val fruitProtein = remember { mutableStateOf("") }

    LaunchedEffect(state) {
        if(state is com.example.nutritrack.data.network.FruitUiState.Success) {
            val fruit = (state as com.example.nutritrack.data.network.FruitUiState.Success).fruit
            fruitFamily.value = fruit.family ?: ""
            fruitCalories.value = fruit.nutritions?.calories?.toString() ?: ""
            fruitFat.value = fruit.nutritions?.fat?.toString() ?: ""
            fruitSugar.value = fruit.nutritions?.sugar?.toString() ?: ""
            fruitCarbs.value = fruit.nutritions?.carbohydrates?.toString() ?: ""
            fruitProtein.value = fruit.nutritions?.protein?.toString() ?: ""
        }
    }

    val sharedPref = context.getSharedPreferences("NutriTrackPrefs", Context.MODE_PRIVATE)
    val savedUserId = sharedPref.getInt("LOGGED_IN_USER_ID", -1)

    LaunchedEffect(savedUserId) {
        if (savedUserId != -1) {
            patientViewModel.getPatientFruitScore(savedUserId)
        }
    }

    val fruitScore = patientViewModel.fruitScore

    // Show modal.
    var showResponsesModal by remember { mutableStateOf(false) }

    // Gen AI values temp.
    val buttonPrompt = stringResource(R.string.buttonPrompt)
    val placeholderResult = stringResource(R.string.placeholder_prompt)
    var prompt by rememberSaveable { mutableStateOf(buttonPrompt) }
    var result by rememberSaveable { mutableStateOf(placeholderResult) }
    val uiState by genAiViewModel.uiState.collectAsState()

    // Flag to check if tip has been inserted into db to avoid duplicates.
    var tipInserted by rememberSaveable { mutableStateOf(false) }

    // Modal saved responses from list generated by viewmodel.
    val userTips = remember { mutableStateOf(listOf<NutriCoachTips>()) }

    if (savedUserId != -1) {
        LaunchedEffect(showResponsesModal) {
            val tips = nutriCoachTipsViewModel.getTipsByUserId(savedUserId)
            userTips.value = tips
        }
    }

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(

        ) {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "NutriCoach",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Fruity API section, need to add condition to check fruit score
            // and only display if fruit score is bad.
            if (fruitScore != null && fruitScore < 5) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        "Fruit Name",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitSearch.value,
                            onValueChange = { fruitSearch.value = it },
                            label = { Text("Enter fruit name.") },
                            modifier = Modifier
                                .width(224.dp)
                        )

                        Button(
                            onClick = { fruitViewModel.searchFruit(fruitSearch.value) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .size(54.dp)
                        ) {
                            Text("Details")
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitFamily.value,
                            onValueChange = { fruitFamily.value = it },
                            label = { Text("Family.") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitCalories.value,
                            onValueChange = { fruitCalories.value = it },
                            label = { Text("Calories.") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitFat.value,
                            onValueChange = { fruitFat.value = it },
                            label = { Text("Fat.") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitSugar.value,
                            onValueChange = { fruitSugar.value = it },
                            label = { Text("Sugar.") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitCarbs.value,
                            onValueChange = { fruitCarbs.value = it },
                            label = { Text("Carbohydrates.") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                    ) {
                        OutlinedTextField(
                            value = fruitProtein.value,
                            onValueChange = { fruitProtein.value = it },
                            label = { Text("Protein.") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.5.dp
                    )
                }
            }

            // GenAI section.
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    "GenAI Section"
                )

                Button(
                    onClick = {
                        tipInserted = false // Resets the tip inserted flag.
                        genAiViewModel.sendPrompt(prompt)
                    },
                    enabled = prompt.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text("Motivational Message (AI)")
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // uistate handling for generating the gen ai text.
                if (uiState is UiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    if (uiState is UiState.Error) {
                        result = (uiState as UiState.Error).errorMesage
                    } else if (uiState is UiState.Success && !tipInserted) {
                        val newTip = (uiState as UiState.Success).outputText
                        result = newTip

                        val response = NutriCoachTips(
                            promptText = result,
                            tipUserId = savedUserId
                        )
                        nutriCoachTipsViewModel.insertTip(response)

                        tipInserted = true
                    }
                    }
                    Text(
                        text = result,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Button(
                    onClick = { showResponsesModal = true },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Show All Tips ")
                }

                if (showResponsesModal) {
                    AlertDialog(
                        onDismissRequest = { showResponsesModal = false },
                        confirmButton = {
                            Button(onClick = { showResponsesModal = false }) {
                                Text("Close")
                            }
                        },
                        title = {
                            Text("All Generated Tips")
                        },
                        text = {
                            if (userTips.value.isNotEmpty()) {
                                LazyColumn(
                                    modifier = Modifier
                                        .height(300.dp)
                                ) {
                                    itemsIndexed(userTips.value) { index, tip ->
                                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                            Text(
                                                text = "${index + 1}. ${tip.promptText}",
                                                fontSize = 14.sp
                                            )
                                            Divider(color = Color.Gray, thickness = 1.dp)
                                        }
                                    }
                                }
                            } else {
                                Text("No tips found.")
                            }
                        }
                    )
                }
        }
    }
}